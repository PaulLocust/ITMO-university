package commands.concreteCommands;

import collection.StudyGroupPackage.StudyGroup;
import commands.auxillary.AbstractCommand;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.Console;
import managers.StudyGroupAsker;

/**
 * Этот класс нужен для добавления новых элементов в коллекцию
 */
public class Add extends AbstractCommand {

    private final CollectionManager collectionManager;
    private final StudyGroupAsker studyGroupAsker;

    public Add(CollectionManager collectionManager, StudyGroupAsker studyGroupAsker) {

        super("add {element}", "Adds new element to collection.");
        this.collectionManager = collectionManager;
        this.studyGroupAsker = studyGroupAsker;
    }

    /**
     * Метод добавляет учебную организацию в коллекцию
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.addToCollection(new StudyGroup(
                    studyGroupAsker.setId(),
                    studyGroupAsker.askStudyGroupName(),
                    studyGroupAsker.askCoordinates(),
                    studyGroupAsker.askCreationDate(),
                    studyGroupAsker.askStudentsCount(),
                    studyGroupAsker.askTransferredStudents(),
                    studyGroupAsker.askAverageMark(),
                    studyGroupAsker.askSemester(),
                    studyGroupAsker.askPerson()
            ));
            Console.printLn("StudyGroup was created successfully");
            return true;
        } catch (WrongAmountOfElementsException e){
            Console.printError("Usage of (" + argument + ") in " + getName());
        } catch (IncorrectInputInScriptException ignored) {
        }
        return false;
    }


}
