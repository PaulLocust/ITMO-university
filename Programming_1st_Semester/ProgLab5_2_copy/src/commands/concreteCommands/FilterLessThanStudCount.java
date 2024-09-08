package commands.concreteCommands;

import collection.StudyGroupPackage.StudyGroup;
import commands.auxillary.AbstractCommand;
import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;

import java.util.HashSet;

/**
 * Этот класс фильтрует элементы коллекции по значению поля studentsCount.
 */
public class FilterLessThanStudCount extends AbstractCommand {
    private final CollectionManager collectionManager;

    public FilterLessThanStudCount(CollectionManager collectionManager) {
        super("filter_less_than_students_count", "Display elements whose studentsCount field value is less than the specified one.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод для показа всех элементов коллекции, значения полей studentsCount которых меньше заданного
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            HashSet<StudyGroup> studyGroupCollection = collectionManager.getCollection();
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();

            long arg = Long.parseLong(argument);
            int i = 0;
            for (StudyGroup group: studyGroupCollection) {
                if (arg > group.getStudentsCount()) {
                    Console.printLn(group.toString());
                    i++;
                }
            }
            // english moment
            if (i == 1)
                System.out.println(i + " object was found");
            else System.out.println(i + " objects were found");

        } catch (WrongAmountOfElementsException exception) {
            Console.printLn("Usage: '" + getName() + "'");
        } catch (EmptyCollectionException e) {
            Console.printError("Collection is empty!");
        }
        return false;
    }


}
