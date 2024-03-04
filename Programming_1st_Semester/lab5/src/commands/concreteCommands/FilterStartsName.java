package commands.concreteCommands;

import collection.StudyGroupPackage.StudyGroup;
import commands.auxillary.AbstractCommand;
import exceptions.EmptyCollectionException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;


import java.util.HashSet;

/**
 * Этот класс фильтрует элементы коллекции по значению поля name класса StudyGroup.
 */
public class FilterStartsName extends AbstractCommand {
    private final CollectionManager collectionManager;



    public FilterStartsName(CollectionManager collectionManager) {
        super("filter_starts_with_name name", "Print elements whose name field value begins with a given substring.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод для показа всех элементов коллекции, имя которых начинается с заданной подстроки
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            HashSet<StudyGroup> studyGroupCollection = collectionManager.getCollection();
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();

            int i = 0;
            for (StudyGroup group: studyGroupCollection) {
                String firstSubstringOfName = (group.getName().trim()).split(" ")[0];
                if (argument.equals(firstSubstringOfName)) {
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
