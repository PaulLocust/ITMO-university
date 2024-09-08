package commands;


import commands.auxillary.AbstractCommand;
import exceptions.EmptyCollectionException;
import exceptions.MustBeNotEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.ResponseOutputer;

/**
 * Класс, убирающий элемент коллекции по его id
 */
public class RemoveById extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "<id>","Remove an element from a collection by its id.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод для удаления элемента коллекции по id
     * @param stringArgument аргумент команды, введённой пользователем
     * @param objectArgument сериализованный объект класса StudyGroup, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try{
            if (stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            int id = Integer.parseInt(stringArgument);
            if(collectionManager.getById(id) == null) throw new MustBeNotEmptyException();
            collectionManager.removeByIdFromCollection(id);
            ResponseOutputer.appendLn("Organization was removed successfully");
            return true;

        } catch (WrongAmountOfElementsException e){
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (NumberFormatException e) {
            ResponseOutputer.appendError("The id have to be an Integer value");
        } catch (MustBeNotEmptyException e) {
            ResponseOutputer.appendError("There is no organization with this id");
        } catch (EmptyCollectionException e) {
            ResponseOutputer.appendError("Collection is empty!");
        }
        return false;
    }


}