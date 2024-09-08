package commands;


import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.ResponseOutputer;

/**
 * Очистка коллекции
 */
public class Clear extends AbstractCommand {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear","","Clear the collection.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод для очистки коллекции от всех элементов
     * @param stringArgument аргумент команды, введённой пользователем
     * @param objectArgument сериализованный объект класса StudyGroup, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            collectionManager.clearCollection();
            ResponseOutputer.appendLn("Collection was cleared");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }


}

