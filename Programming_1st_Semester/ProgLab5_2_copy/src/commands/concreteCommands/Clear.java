package commands.concreteCommands;

import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;

/**
 * Очистка коллекции
 */
public class Clear extends AbstractCommand {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "Clear the collection.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод для очистки коллекции от всех элементов
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.clearCollection();
            Console.printLn("Collection was cleared");
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("Usage of (" + argument + ") in " + getName());
        }
        return false;
    }


}
