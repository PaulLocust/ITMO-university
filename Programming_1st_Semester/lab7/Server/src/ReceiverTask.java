import answers.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import java.util.logging.Logger;

public class ReceiverTask implements Runnable {
    public static final Logger logger = Logger.getLogger(Receiver.class.getName());
    private final DatagramSocket socket;
    private final Interpreter interpreter;


    public ReceiverTask(DatagramSocket socket, Interpreter interpreter) {
        this.socket = socket;
        this.interpreter = interpreter;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                byte[] bytes = new byte[16384];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                socket.receive(datagramPacket);

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

                Request request = (Request) objectInputStream.readObject();
                logger.info("Request received from" + datagramPacket.getAddress() + ":" +
                        datagramPacket.getPort() + " - " + request.getCommand().getName() + Arrays.toString(request.getArgs()));
                interpreter.putCommand(request,datagramPacket);
                byteArrayInputStream.close();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                logger.severe( "Couldn't receive a request!");
            }
        }
    }
}
