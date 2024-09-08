import commands.*;
import utility.CollectionFileManager;
import utility.CollectionManager;
import utility.CommandManager;
import utility.RequestHandler;

import java.util.logging.Logger;

public class App {

    public static final int PORT = 1821;



    public static void main(String[] args) {
        String filename;
        if (args.length == 0) {
            filename = "studyGroups.json"; //дефолтный файл
        } else filename = System.getenv(args[0]);

        CollectionFileManager collectionFileManager = new CollectionFileManager(filename);
        CollectionManager collectionManager = new CollectionManager(collectionFileManager);
        CommandManager commandManager = new CommandManager(
                new Help(),
                new Info(collectionManager),
                new Show(collectionManager),
                new Add(collectionManager),
                new UpdateId(collectionManager),
                new RemoveById(collectionManager),
                new Clear(collectionManager),
                new Save(collectionManager),
                new ExecuteScript(),
                new Exit(),
                new RemoveGreater(collectionManager),
                new RemoveLower(collectionManager),
                new History(),
                new SumOfTransferredStudents(collectionManager),
                new FilterStartsName(collectionManager),
                new FilterLessThanStudCount(collectionManager),
                new ServerExitCommand()
        );
        RequestHandler requestHandler = new RequestHandler(commandManager);
        Server server = new Server(PORT, requestHandler);
        server.run();
    }
}