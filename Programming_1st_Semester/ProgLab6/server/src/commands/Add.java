package commands;



import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import models.StudyGroup;
import utility.CollectionManager;
import utility.Outputer;
import utility.ResponseOutputer;

import java.time.ZonedDateTime;

/**
 * Этот класс нужен для добавления новых элементов в коллекцию
 */
public class Add extends AbstractCommand {

    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {

        super("add","{element}", "Adds new element to collection.");
        this.collectionManager = collectionManager;
    }

    /**
     * Метод добавляет учебную организацию в коллекцию
     * @param stringArgument аргумент команды, введённой пользователем
     * @param objectArgument сериализованный объект класса StudyGroup, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try{
            if (!stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            StudyGroup studyGroup = (StudyGroup) objectArgument;
            collectionManager.addToCollection(new StudyGroup(
                    collectionManager.generateNewIdForCollection(),
                    studyGroup.getName(),
                    studyGroup.getCoordinates(),
                    ZonedDateTime.now(),
                    studyGroup.getStudentsCount(),
                    studyGroup.getTransferredStudents(),
                    studyGroup.getAverageMark(),
                    studyGroup.getSemesterEnum(),
                    studyGroup.getGroupAdmin()
            ));
            ResponseOutputer.appendLn("StudyGroup was created successfully");
            return true;
        } catch (WrongAmountOfElementsException e){
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (ClassCastException exception) {
            ResponseOutputer.appendError("The object passed by the client is incorrect!");
        }
        return false;
    }


}