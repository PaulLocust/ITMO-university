package commands.concreteCommands;

import collection.StudyGroupPackage.StudyGroup;
import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;

import java.util.ArrayList;

/**
 * Класс, показывающий состояние всех полей каждого из элементов коллекции
 */
public class Show extends AbstractCommand {
    private final CollectionManager collectionManager;
    public Show(CollectionManager collectionManager) {
        super("show", "Print to standard output all elements of the collection in string representation.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод для показа состояние всех полей каждого из элементов коллекции
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            ArrayList<StudyGroup> copyOfCollection = new ArrayList<>(collectionManager.getCollection());
            for (StudyGroup studyGroup : copyOfCollection) {
                Console.printLn(studyGroup.toString() + "\n============");
            }
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("No arguments in " + getName());
        }
        return false;
    }


}
