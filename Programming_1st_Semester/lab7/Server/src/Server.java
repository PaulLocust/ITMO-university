
import exceptions.CommandAlreadyExistsException;
import exceptions.RightException;
import exceptions.SameIdException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Запускает сервер.
 */
public class Server {
    public static int SERVER_PORT = 12501;
    public static final Logger logger = Logger.getLogger(Server.class.getName());
    String source;
    private final FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    private static ThreadPoolExecutor executor;

    private static String sqlLogin, sqlPassword;

    public static void main(String[] args) throws SameIdException, InvocationTargetException, IllegalAccessException,
            InstantiationException, RightException, NoSuchMethodException, CommandAlreadyExistsException, IOException, ClassNotFoundException {

        String envVariable;
        /*if (args.length<3){
            throw new ArrayIndexOutOfBoundsException();
        }*/
        /**
         * ПОТОМ УБРАТЬ И ДОБАВИТЬ ВСЁ НЕОБХОДИМОЕ!!!!!
         * */
        envVariable = "env"; //args[0];
        sqlLogin = "s######"; //args[1];
        sqlPassword = "cat .pgpass"; //args[2];

        Server server = new Server(envVariable);
        server.launch();
    }

    public Server(String source) throws IOException {
        this.source=source;
        fileTxt=new FileHandler(source);
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
    }

    public void launch() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, CommandAlreadyExistsException, SameIdException, RightException, IOException, ClassNotFoundException {
        DatagramSocket socket = setSocket();
        if (socket != null) {
            logger.info("The server is running");

            Scanner scanner = new Scanner(System.in);
            Sender sender = new Sender(socket);
            Interpreter interpreter = new Interpreter(sender,socket, sqlLogin, sqlPassword);
            interpreter.start();
            Receiver receiver = new Receiver(socket, interpreter);


            receiver.setDaemon(true);
            receiver.start();

            shutDownHook();
            while (true) {
                interpreter.askCommand(scanner);
            }
        }
    }

    public DatagramSocket setSocket() {
        try {
            return new DatagramSocket(SERVER_PORT);
        } catch (SocketException e) {
            logger.severe( "Error with socket. Try again later!");

        }
        return null;
    }

    private void shutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> logger.info("The server is stopped")));
    }
}