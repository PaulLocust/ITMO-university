package commands;



import commands.auxillary.AbstractCommand;
import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsException;
import models.StudyGroup;
import utility.CollectionManager;
import utility.Outputer;
import utility.ResponseOutputer;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Класс, удаляющий все элементы коллекции, поле studentsCount которых больше значения поля targetElement.
 * targetElement - целевой элемент, по которому идёт сравнение, он выбирается по id в аргументе команды
 */
public class RemoveGreater extends AbstractCommand {

    private final CollectionManager collectionManager;
    private StudyGroup targetElement;

    public RemoveGreater(CollectionManager collectionManager) {
        super("remove_greater", "<target_id>","Remove from a collection all elements greater than a given value.");
        this.collectionManager = collectionManager;

    }

    /**
     * Метод для удаления всех элементов коллекции, поле studentsCount которых больше значения поля targetElement.
     * @param stringArgument аргумент команды, введённой пользователем
     * @param objectArgument сериализованный объект класса StudyGroup, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            HashSet<StudyGroup> studyGroupCollection = collectionManager.getCollection();
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();

            for (StudyGroup group: studyGroupCollection) {
                int id = group.getId();
                if(Integer.toString(id).equals(stringArgument)) {
                    targetElement = group;
                    break;
                }
            }

            Iterator<StudyGroup> iter = studyGroupCollection.iterator();
            int i = 0;
            while(iter.hasNext()) {
                if (targetElement.getStudentsCount() < iter.next().getStudentsCount()) {
                    iter.remove();
                    i++;
                }
            }
            // english moment
            if (i == 1)
                ResponseOutputer.appendLn(i + " object removed");
            else ResponseOutputer.appendLn(i + " objects removed");

        } catch (WrongAmountOfElementsException e) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (EmptyCollectionException e) {
            ResponseOutputer.appendError("Collection is empty!");
        } catch (NullPointerException e) {
            ResponseOutputer.appendError("There is no target element with such id!");
        }
        return false;
    }


}
