package commands.concreteCommands;

import collection.StudyGroupPackage.StudyGroup;
import commands.auxillary.AbstractCommand;
import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;
import java.util.HashSet;

/**
 * Этот класс показывает сумму переведённых студентов из каждого элемента коллекции
 */
public class SumOfTransferredStudents extends AbstractCommand {
    private final CollectionManager collectionManager;

    public SumOfTransferredStudents(CollectionManager collectionManager) {
        super("sum_of_transferred_students", "Display the sum of the values of the transferredStudents field for all elements of the collection");
        this.collectionManager = collectionManager;

    }

    /**
     * Этот метод выводит сумму переведённых студентов
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            HashSet<StudyGroup> studyGroupCollection = collectionManager.getCollection();
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();

            long transferredStudentsCount = 0;
            for (StudyGroup group: studyGroupCollection) {
                transferredStudentsCount += group.getTransferredStudents();
            }
            System.out.println(transferredStudentsCount);

        } catch (WrongAmountOfElementsException exception) {
            Console.printLn("Usage: '" + getName() + "'");
        } catch (EmptyCollectionException e) {
            Console.printError("Collection is empty!");
        }
        return false;
    }
}
