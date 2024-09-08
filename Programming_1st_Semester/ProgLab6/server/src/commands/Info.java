package commands;


import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.ResponseOutputer;

import java.time.ZonedDateTime;

/**
 * Класс для получения информации о коллекции
 */
public class Info extends AbstractCommand {
    private final CollectionManager collectionManager;
    public Info(CollectionManager collectionManager) {
        super("info", "","Prints information about the collection(type, initialization date, number of elements, etc.) to standard output.");
        this.collectionManager = collectionManager;
    }


    /**
     * Метод для получения информации о коллекции
     * @param stringArgument аргумент команды, введённой пользователем
     * @param objectArgument сериализованный объект класса StudyGroup, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try{
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            ZonedDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "initialization has not yet occurred in this session" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            ZonedDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "there has not been a save in this session yet" :
                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            ResponseOutputer.appendLn("Information about the collection:");
            ResponseOutputer.appendLn(" Type: " + collectionManager.collectionType());
            ResponseOutputer.appendLn(" Number of elements: " + collectionManager.collectionSize());
            ResponseOutputer.appendLn(" Last save time: " + lastSaveTimeString);
            ResponseOutputer.appendLn(" Last init time: " + lastInitTimeString);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }

}