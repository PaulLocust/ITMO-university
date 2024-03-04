package commands.concreteCommands;

import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;

/**
 * Класс для получения информации о коллекции
 */
public class Info extends AbstractCommand {
    private final CollectionManager collectionManager;
    public Info(CollectionManager collectionManager) {
        super("info", "Prints information about the collection(type, initialization date, number of elements, etc.) to standard output.");
        this.collectionManager = collectionManager;
    }


    /**
     * Метод для получения информации о коллекции
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.printLn(collectionManager.infoAboutCollection());
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("Usage of (" + argument + ") in " + getName());
        }
        return false;
    }

}
