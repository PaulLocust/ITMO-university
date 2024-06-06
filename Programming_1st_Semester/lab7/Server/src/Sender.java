import answers.Answer;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sender {
    private final ExecutorService pool;
    private final DatagramSocket socket;

    public Sender(DatagramSocket socket) {
        // Используем Cached Thread Pool
        pool = Executors.newCachedThreadPool();
        this.socket = socket;
    }

    public void send(Answer answer, InetAddress address, int port) {
        SenderTask task = new SenderTask(socket, answer, address, port);
        pool.execute(task);
    }

}