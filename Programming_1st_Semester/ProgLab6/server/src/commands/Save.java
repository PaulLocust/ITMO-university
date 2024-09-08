package commands;

import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.ResponseOutputer;

/**
 * Сохранение коллекции в файл
 */
public class Save extends AbstractCommand {
    private final CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        super("save", "","Save the collection to a file.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод для сохранения элементов коллекции в файл
     * @param stringArgument аргумент команды, введённой пользователем
     * @param objectArgument сериализованный объект класса StudyGroup, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            collectionManager.saveCollection();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }


}