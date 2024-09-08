package commands.concreteCommands;

import collection.StudyGroupPackage.StudyGroup;
import commands.auxillary.AbstractCommand;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;
import managers.StudyGroupAsker;

/**
 * Класс, который нужен для обновления какого-либо элемента коллекции
 */
public class UpdateId extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final StudyGroupAsker studyGroupAsker;

    public UpdateId(CollectionManager collectionManager, StudyGroupAsker studyGroupAsker) {
        super("update id {element}", "Updating the value of a collection element whose identifier is equal to the given one.");
        this.collectionManager = collectionManager;
        this.studyGroupAsker = studyGroupAsker;
    }

    /**
     * Метод для обновления элемента коллекции
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try{
            if(argument.isEmpty()) throw new WrongAmountOfElementsException();
            int id = Integer.parseInt(argument);
            if(collectionManager.getById(id) == null) throw new MustBeNotEmptyException();
            StudyGroup newStudyGroup = new StudyGroup(
                    studyGroupAsker.setId(),
                    studyGroupAsker.askStudyGroupName(),
                    studyGroupAsker.askCoordinates(),
                    studyGroupAsker.askCreationDate(),
                    studyGroupAsker.askStudentsCount(),
                    studyGroupAsker.askTransferredStudents(),
                    studyGroupAsker.askAverageMark(),
                    studyGroupAsker.askSemester(),
                    studyGroupAsker.askPerson()
            );
            collectionManager.replaceById(id, newStudyGroup);
            Console.printLn("StudyGroup was updated successfully");
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("No arguments in " + getName());
        } catch (NumberFormatException e) {
            Console.printError("The id have to be an Integer value");
        } catch (MustBeNotEmptyException e) {
            Console.printError("There is no StudyGroup with this id");
        } catch (IncorrectInputInScriptException ignored) {
        }
        return false;
    }


}
