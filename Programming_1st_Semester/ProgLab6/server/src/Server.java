import exceptions.OpeningServerSocketException;
import interaction.Request;
import interaction.Response;
import interaction.ResponseResult;
import org.apache.commons.lang3.SerializationUtils;
import utility.Outputer;
import utility.RequestHandler;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Запускает сервер.
 */
public class Server {
    private final int port;
    private DatagramChannel datagramChannel;
    private final RequestHandler requestHandler;

    public Server(int port, RequestHandler requestHandler) {
        this.port = port;
        this.requestHandler = requestHandler;
    }

    public void run() {
        try {
            openServerSocket();
            processClientRequests();
        } catch (OpeningServerSocketException exception) {
            Outputer.printError("The server cannot be started!");
        }
    }

    private void openServerSocket() throws OpeningServerSocketException {
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.socket().bind(new InetSocketAddress(port));
            Outputer.printLn("The server has been successfully started.");
        } catch (IOException exception) {
            Outputer.printError("An error occurred while trying to use the port '" + port + "'!");
            throw new OpeningServerSocketException();
        }
    }

    private void processClientRequests() {
        int bufferSize = 4096;
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        while (true) {
            try {
                buffer.clear();
                InetSocketAddress clientAddress = (InetSocketAddress) datagramChannel.receive(buffer);
                buffer.flip();
                byte[] requestData = new byte[buffer.remaining()];
                buffer.get(requestData);
                Request userRequest = SerializationUtils.deserialize(requestData);
                Response responseToUser = requestHandler.handle(userRequest);
                // Send ping signal to the client indicating server connection status
                //Response pingResponse = new Response(ResponseResult.SERVER_PING, "Server is connected.");

                //byte[] pingData = SerializationUtils.serialize(pingResponse);
                //ByteBuffer pingBuffer = ByteBuffer.wrap(pingData);
                //datagramChannel.send(pingBuffer, clientAddress);

                byte[] responseData = SerializationUtils.serialize(responseToUser);
                ByteBuffer responseBuffer = ByteBuffer.wrap(responseData);
                datagramChannel.send(responseBuffer, clientAddress);
                if (responseToUser.getResponseResult() == ResponseResult.SERVER_EXIT) {
                    break;
                }
            } catch (IOException exception) {
                Outputer.printError("An error occurred while processing client requests.");
            }
        }
    }
}