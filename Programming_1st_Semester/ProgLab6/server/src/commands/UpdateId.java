package commands;


import commands.auxillary.AbstractCommand;
import exceptions.EmptyCollectionException;
import exceptions.StudyGroupNotFound;
import exceptions.WrongAmountOfElementsException;
import models.Coordinates;
import models.Person;
import models.Semester;
import models.StudyGroup;
import utility.CollectionManager;
import utility.ResponseOutputer;

import java.time.ZonedDateTime;

/**
 * Класс, который нужен для обновления какого-либо элемента коллекции
 */
public class UpdateId extends AbstractCommand {

    private final CollectionManager collectionManager;

    public UpdateId(CollectionManager collectionManager) {
        super("update", "<id> {element}","Updating the value of a collection element whose identifier is equal to the given one.");
        this.collectionManager = collectionManager;

    }

    /**
     * Метод для обновления элемента коллекции
     * @param stringArgument аргумент команды, введённой пользователем
     * @param objectArgument сериализованный объект класса StudyGroup, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try{
            if (stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();

            int id = Integer.parseInt(stringArgument);
            if (id <= 0) throw new NumberFormatException();
            StudyGroup oldStudyGroup = collectionManager.getById(id);
            if (oldStudyGroup == null) throw new StudyGroupNotFound();

            StudyGroup studyGroup = (StudyGroup) objectArgument;

            String name = studyGroup.getName() == null ? oldStudyGroup.getName() : studyGroup.getName();
            Coordinates coordinates = (studyGroup.getCoordinates() == null || studyGroup.getCoordinates().getY() == null || studyGroup.getCoordinates().getY() > 849) ? oldStudyGroup.getCoordinates() : studyGroup.getCoordinates();
            ZonedDateTime creationDate = oldStudyGroup.getCreationDate();
            Long studentsCount = studyGroup.getStudentsCount() < 0 ? oldStudyGroup.getStudentsCount() : studyGroup.getStudentsCount();
            long transferred = studyGroup.getTransferredStudents() <= 0 ? oldStudyGroup.getTransferredStudents() : studyGroup.getTransferredStudents();
            Long averageMark = studyGroup.getAverageMark() < 0 ? oldStudyGroup.getAverageMark() : studyGroup.getAverageMark();
            Semester semesterEnum = studyGroup.getSemesterEnum() == null ? oldStudyGroup.getSemesterEnum() : studyGroup.getSemesterEnum();
            Person groupAdmin = (studyGroup.getGroupAdmin() != null && studyGroup.getGroupAdmin().getName() != null && studyGroup.getGroupAdmin().getHeight() > 0 && studyGroup.getGroupAdmin().getEyeColor() != null) ? oldStudyGroup.getGroupAdmin() : studyGroup.getGroupAdmin();

            collectionManager.removeFromCollection(oldStudyGroup);
            collectionManager.addToCollection(new StudyGroup(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    studentsCount,
                    transferred,
                    averageMark,
                    semesterEnum,
                    groupAdmin
            ));
            ResponseOutputer.appendLn("StudyGroup was updated successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (EmptyCollectionException exception) {
            ResponseOutputer.appendError("Collection is empty!");
        } catch (NumberFormatException exception) {
            ResponseOutputer.appendError("ID have to be a positive value!");
        } catch (StudyGroupNotFound exception) {
            ResponseOutputer.appendError("There is no study group with this ID in the collection!");
        } catch (ClassCastException exception) {
            ResponseOutputer.appendError("The object passed by the client is incorrect!");
        }
        return false;
    }


}
