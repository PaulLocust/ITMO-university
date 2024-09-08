package commands.concreteCommands;

import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;
import managers.FileManager;

/**
 * Сохранение коллекции в файл
 */
public class Save extends AbstractCommand {
    private final FileManager fileManager;
    private final CollectionManager collectionManager;

    public Save(FileManager fileManager, CollectionManager collectionManager) {
        super("save", "Save the collection to a file.");
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    /**
     * Метод для сохранения элементов коллекции в файл
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            fileManager.writeCollection(collectionManager.getCollection());
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("No arguments in " + getName());
        }
        return false;
    }


}
