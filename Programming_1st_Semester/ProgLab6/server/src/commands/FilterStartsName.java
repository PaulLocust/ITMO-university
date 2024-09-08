package commands;



import commands.auxillary.AbstractCommand;
import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsException;
import models.StudyGroup;
import utility.CollectionManager;
import utility.Outputer;
import utility.ResponseOutputer;

import java.util.HashSet;

/**
 * Этот класс фильтрует элементы коллекции по значению поля name класса StudyGroup.
 */
public class FilterStartsName extends AbstractCommand {
    private final CollectionManager collectionManager;



    public FilterStartsName(CollectionManager collectionManager) {
        super("filter_starts_with_name", "<name>","Print elements whose name field value begins with a given substring.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод для показа всех элементов коллекции, имя которых начинается с заданной подстроки
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

            int i = 0;
            for (StudyGroup group: studyGroupCollection) {
                String firstSubstringOfName = (group.getName().trim()).split(" ")[0];
                if (stringArgument.equals(firstSubstringOfName)) {
                    ResponseOutputer.appendLn(group.toString());
                    i++;
                }
            }
            // english moment
            if (i == 1)
                ResponseOutputer.appendLn(i + " object was found");
            else ResponseOutputer.appendLn(i + " objects were found");

        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (EmptyCollectionException e) {
            ResponseOutputer.appendError("Collection is empty!");
        }
        return false;
    }


}