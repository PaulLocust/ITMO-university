package commands;


import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.ResponseOutputer;

/**
 * Класс, показывающий состояние всех полей каждого из элементов коллекции
 */
public class Show extends AbstractCommand {
    private final CollectionManager collectionManager;
    public Show(CollectionManager collectionManager) {
        super("show", "","Print to standard output all elements of the collection in string representation.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод для показа состояние всех полей каждого из элементов коллекции
     * @param stringArgument аргумент команды, введённой пользователем
     * @param objectArgument сериализованный объект класса StudyGroup, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            ResponseOutputer.appendLn(collectionManager.showCollection());
            return true;
        } catch (WrongAmountOfElementsException e){
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }


}