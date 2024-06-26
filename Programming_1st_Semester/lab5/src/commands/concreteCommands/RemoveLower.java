package commands.concreteCommands;

import collection.StudyGroupPackage.StudyGroup;
import commands.auxillary.AbstractCommand;
import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Класс, удаляющий все элементы коллекции, поле studentsCount которых меньше значения поля targetElement.
 * targetElement - целевой элемент, по которому идёт сравнение, он выбирается по id в аргументе команды
 */
public class RemoveLower extends AbstractCommand {
    private final CollectionManager collectionManager;

    private StudyGroup targetElement;

    public RemoveLower(CollectionManager collectionManager) {
        super("remove_lower id {element}", "Remove from a collection all elements smaller than a given one.");
        this.collectionManager = collectionManager;

    }

    /**
     * Метод для удаления всех элементов коллекции, поле studentsCount которых меньше значения поля targetElement.
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            HashSet<StudyGroup> studyGroupCollection = collectionManager.getCollection();
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();

            for (StudyGroup group: studyGroupCollection) {
                int id = group.getId();
                if(Integer.toString(id).equals(argument)) {
                    targetElement = group;
                    break;
                }
            }

            Iterator<StudyGroup> iter = studyGroupCollection.iterator();
            int i = 0;
            while(iter.hasNext()) {
                if (targetElement.getStudentsCount() > iter.next().getStudentsCount()) {
                    iter.remove();
                    i++;
                }
            }
            // english moment
            if (i == 1)
                System.out.println(i + " object removed");
            else System.out.println(i + " objects removed");

        } catch (WrongAmountOfElementsException exception) {
            Console.printLn("Usage: '" + getName() + "'");
        } catch (EmptyCollectionException e) {
            Console.printError("Collection is empty!");
        }
        return false;
    }


}
