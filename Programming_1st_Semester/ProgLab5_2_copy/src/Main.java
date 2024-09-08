import commands.concreteCommands.*;
import managers.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String filename;
        if (args.length == 0) {
            filename = "db.json"; //дефолтный файл
        } else filename = System.getenv(args[0]);

        try (Scanner userScanner = new Scanner(System.in)) {

            CollectionManager collectionManager = new CollectionManager();
            StudyGroupAsker studyGroupAsker = new StudyGroupAsker(collectionManager, userScanner);
            FileManager fileManager = new FileManager(filename);
            CommandManager commandManager = new CommandManager(
                    new Help(),
                    new Info(collectionManager),
                    new Show(collectionManager),
                    new Add(collectionManager, studyGroupAsker),
                    new UpdateId(collectionManager, studyGroupAsker),
                    new RemoveById(collectionManager),
                    new Clear(collectionManager),
                    new Save(fileManager, collectionManager),
                    new ExecuteScript(),
                    new Exit(),
                    new RemoveGreater(collectionManager),
                    new RemoveLower(collectionManager),
                    new History(),
                    new SumOfTransferredStudents(collectionManager),
                    new FilterStartsName(collectionManager),
                    new FilterLessThanStudCount(collectionManager)

            );

            if(args.length == 0){
                Console.printLn("Using default filename: " + filename);
                collectionManager.setCollection(fileManager.readCollection());
            } else if(args.length > 1){
                Console.printError("More arguments than expected! (" + args.length  +", 1 expected)");
                commandManager.exit("");
            } else {
                fileManager.setFilename(filename);
                if(!fileManager.readCollection().isEmpty()){
                    filename = args[0];
                    Console.printLn("Using file " + filename);
                    collectionManager.setCollection(fileManager.readCollection());
                }
            }

             Console console = new Console(commandManager, userScanner, studyGroupAsker);
             console.interactiveMode();
        }


    }
}
