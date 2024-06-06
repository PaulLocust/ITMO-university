import java.net.DatagramSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Receiver extends Thread {
    private final ThreadPoolExecutor executor;
    private final DatagramSocket socket;
    private final Interpreter interpreter;


    public Receiver(DatagramSocket socket, Interpreter interpreter) {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        this.socket = socket;
        this.interpreter = interpreter;
    }

    @Override
    public void run() {
        ReceiverTask task = new ReceiverTask(socket, interpreter);
        executor.execute(task);
    }
}