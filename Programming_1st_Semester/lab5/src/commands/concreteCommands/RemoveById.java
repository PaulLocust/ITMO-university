package commands.concreteCommands;

import commands.auxillary.AbstractCommand;
import exceptions.MustBeNotEmptyException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;

/**
 * Класс, убирающий элемент коллекции по его id
 */
public class RemoveById extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id id", "Remove an element from a collection by its id.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод для удаления элемента коллекции по id
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try{
            if(argument.isEmpty()) throw new WrongAmountOfElementsException();
            int id = Integer.parseInt(argument);
            if(collectionManager.getById(id) == null) throw new MustBeNotEmptyException();
            collectionManager.removeByIdFromCollection(id);
            Console.printLn("StudyGroup was removed successfully");
            return true;

        } catch (WrongAmountOfElementsException e){
            Console.printError("No arguments in " + getName());
        } catch (NumberFormatException e) {
            Console.printError("The id have to be an Integer value");
        } catch (MustBeNotEmptyException e) {
            Console.printError("There is no StudyGroup with this id");
        }
        return false;
    }


}
