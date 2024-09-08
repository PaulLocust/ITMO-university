import exceptions.ConnectionErrorException;
import exceptions.NotInDeclaredLimitsException;
import interaction.Request;
import interaction.Response;
import utility.Outputer;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import org.apache.commons.lang3.SerializationUtils;
import java.nio.channels.DatagramChannel;

public class Client {
    private final String host;
    private final int port;
    private final int reconnectionTimeout;
    private int reconnectionAttempts;
    private final int maxReconnectionAttempts;
    private final UserHandler userHandler;
    private DatagramChannel datagramChannel;

    public Client(String host, int port, int reconnectionTimeout, int maxReconnectionAttempts, UserHandler userHandler) {
        this.host = host;
        this.port = port;
        this.reconnectionTimeout = reconnectionTimeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
        this.userHandler = userHandler;
    }

    public void run() {
        try {
            boolean processingStatus = true;
            while (processingStatus) {
                try {
                    connectToServer();
                    processingStatus = processRequestToServer();
                } catch (ConnectionErrorException exception) {
                    if (reconnectionAttempts >= maxReconnectionAttempts) {
                        Outputer.printError("Exceeded the number of connection attempts!");
                        break;
                    }
                    try {
                        Thread.sleep(reconnectionTimeout);
                    } catch (InterruptedException e) {
                        Outputer.printError("Interrupted while waiting for reconnection.");
                    }
                    reconnectionAttempts++;
                }
            }
            if (datagramChannel != null) datagramChannel.close();
            Outputer.printLn("The client's work has been successfully completed.");
        } catch (IOException | NotInDeclaredLimitsException exception) {
            Outputer.printError("An error occurred while running the client.");
        }
    }

    private void connectToServer() throws ConnectionErrorException, NotInDeclaredLimitsException {
        try {
            if (reconnectionAttempts >= 1) Outputer.printLn("Reconnecting to the server...");
            datagramChannel = DatagramChannel.open();
            Outputer.printLn("Datagram channel was opened!");
        } catch (IOException exception) {
            Outputer.printError("An error occurred while connecting to the server!");
            throw new ConnectionErrorException();
        }
    }

    private boolean processRequestToServer() throws NotInDeclaredLimitsException, ConnectionErrorException {
        Request requestToServer = null;
        Response serverResponse = null;
        long startTime = System.currentTimeMillis();
        do {
            try {
                if (System.currentTimeMillis() - startTime > 5000) {
                    if (reconnectionAttempts >= maxReconnectionAttempts) {
                        Outputer.printError("The server is not responding. Exiting.");
                        return false;
                    } else {
                        reconnectionAttempts++;
                        connectToServer();
                        startTime = System.currentTimeMillis();
                    }
                }
                requestToServer = serverResponse != null ? userHandler.handle(serverResponse.getResponseResult()) :
                        userHandler.handle(null);
                if (requestToServer.isEmpty()) continue;
                byte[] requestData = SerializationUtils.serialize(requestToServer);
                ByteBuffer buffer = ByteBuffer.wrap(requestData);
                datagramChannel.send(buffer, new InetSocketAddress(host, port));
                ByteBuffer receiveBuffer = ByteBuffer.allocate(4096);
                datagramChannel.receive(receiveBuffer);
                receiveBuffer.flip();
                byte[] responseData = new byte[receiveBuffer.remaining()];
                receiveBuffer.get(responseData);
                serverResponse = SerializationUtils.deserialize(responseData);
                Outputer.print(serverResponse.getResponseBody());
            } catch (IOException exception) {
                Outputer.printError("An error occurred while communicating with the server.");
            }
        } while (!requestToServer.getCommandName().equals("exit"));
        return false;
    }
}
