import exceptions.CommandUsageException;
import exceptions.IncorrectInputInScriptException;
import exceptions.ScriptRecursionException;
import interaction.Request;
import interaction.ResponseResult;
import models.Coordinates;
import models.Person;
import models.Semester;
import models.StudyGroup;
import utility.Outputer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class UserHandler {

    private Scanner userScanner;
    private final Stack<File> scriptStack = new Stack<>();
    private final Stack<Scanner> scannerStack = new Stack<>();

    public UserHandler(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * Receives user input.
     *
     * @param serverResponseResult Last server's response code.
     * @return New request to server.
     */
    public Request handle(ResponseResult serverResponseResult) {
        String userInput;
        String[] userCommand;
        ProcessingResult processingResult;
        int rewriteAttempts = 0;
        try{
            do{
                try{
                    if(fileMode() && (serverResponseResult == ResponseResult.ERROR ||
                            serverResponseResult == ResponseResult.SERVER_EXIT))
                        throw new IncorrectInputInScriptException();
                    while(fileMode() && !userScanner.hasNextLine()){
                        userScanner.close();
                        userScanner = scannerStack.pop();
                        Outputer.printLn("Going back to the script '" + scriptStack.pop().getName() + "'...");
                    }
                    if(fileMode()){
                        userInput = userScanner.nextLine();
                        if(!userInput.isEmpty()){
                            Outputer.print(App.PS1);
                            Outputer.printLn(userInput);
                        }
                    } else {
                        Outputer.print(App.PS1);
                        userInput = userScanner.nextLine();
                    }
                    userCommand = (userInput.trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                } catch (NoSuchElementException | IllegalStateException exception) {
                    Outputer.println();
                    Outputer.printError("An error occurred while entering the command!");
                    userCommand = new String[]{"", ""};
                    rewriteAttempts++;
                    int maxRewriteAttempts = 3;
                    if (rewriteAttempts >= maxRewriteAttempts) {
                        Outputer.printError("Exceeded the number of input attempts!");
                        System.exit(0);
                    }
                }
                processingResult = processCommand(userCommand[0], userCommand[1]);
            } while (processingResult == ProcessingResult.ERROR && !fileMode() || userCommand[0].isEmpty());
            try{
                if(fileMode() && (serverResponseResult == ResponseResult.ERROR || processingResult == ProcessingResult.ERROR))
                    throw new IncorrectInputInScriptException();
                switch (processingResult) {
                    case OBJECT -> {
                        StudyGroup studyGroupAdd = generateStudyGroupAdd();
                        return new Request(userCommand[0], userCommand[1], studyGroupAdd);
                    }
                    case UPDATE_OBJECT -> {
                        StudyGroup studyGroupUpdate = generateStudyGroupUpdate();
                        return new Request(userCommand[0], userCommand[1], studyGroupUpdate);
                    }
                    case SCRIPT -> {
                        File scriptFile = new File(userCommand[1]);
                        if (!scriptFile.exists()) throw new FileNotFoundException();
                        if (!scriptStack.isEmpty() && scriptStack.search(scriptFile) != -1)
                            throw new ScriptRecursionException();
                        scannerStack.push(userScanner);
                        scriptStack.push(scriptFile);
                        userScanner = new Scanner(scriptFile);
                        Outputer.printLn("Execute script '" + scriptFile.getName() + "'...");
                    }
                }
            } catch (FileNotFoundException exception){
                Outputer.printError("The script file was not found!");
            } catch (ScriptRecursionException exception){
                Outputer.printError("Scripts cannot be called recursively!");
                throw new IncorrectInputInScriptException();
            }
        } catch (IncorrectInputInScriptException exception){
            Outputer.printError("Script execution aborted!");
            while (!scannerStack.isEmpty()) {
                userScanner.close();
                userScanner = scannerStack.pop();
            }
            scriptStack.clear();
            return new Request();
        }
        return new Request(userCommand[0], userCommand[1]);
    }

    /**
     * Processes the entered command.
     *
     * @return Status of code.
     */
    private ProcessingResult processCommand(String command, String commandArgument) {
        try{
            switch (command) {
                case "" -> {
                    return ProcessingResult.ERROR;
                }
                case "add" -> {
                    if (!commandArgument.isEmpty()) throw new CommandUsageException("{element}");
                    return ProcessingResult.OBJECT;
                }
                case "clear", "exit", "help", "history", "info", "save", "sum_of_transferred_students", "server_exit", "show" -> {
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                }
                case "execute_script" -> {
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<file_name>");
                    return ProcessingResult.SCRIPT;
                }
                case "remove_greater", "remove_lower" -> {
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<target_id>");
                }
                case "remove_by_id" -> {
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<id>");
                }
                case "filter_starts_with_name" -> {
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<start_string>");
                }
                case "filter_less_than_students_count" -> {
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<value_to_compare>");
                }
                case "update" -> {
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<id> {element}");
                    return ProcessingResult.UPDATE_OBJECT;
                }
                default -> {
                    Outputer.printLn("Command '" + command + "' was not found. Type 'help' for help.");
                    return ProcessingResult.ERROR;
                }
            }
        } catch (CommandUsageException exception){
            if (exception.getMessage() != null) command += " " + exception.getMessage();
            Outputer.printLn("Usage: '" + command + "'");
            return ProcessingResult.ERROR;
        }
        return ProcessingResult.OK;
    }

    /**
     * Generates studyGroups to add.
     *
     * @return StudyGroup to add.
     * @throws IncorrectInputInScriptException When something went wrong in script.
     */
    private StudyGroup generateStudyGroupAdd() throws IncorrectInputInScriptException {
        StudyGroupAsker studyGroupAsker = new StudyGroupAsker(userScanner);
        if (fileMode()) studyGroupAsker.setScriptMode();
        return new StudyGroup(
                studyGroupAsker.askStudyGroupName(),
                studyGroupAsker.askCoordinates(),
                studyGroupAsker.askStudentsCount(),
                studyGroupAsker.askTransferredStudents(),
                studyGroupAsker.askAverageMark(),
                studyGroupAsker.askSemester(),
                studyGroupAsker.askPerson()
        );
    }

    /**
     * Generates studyGroups to update.
     *
     * @return StudyGroup to update.
     * @throws IncorrectInputInScriptException When something went wrong in script.
     */
    private StudyGroup generateStudyGroupUpdate() throws IncorrectInputInScriptException {
        StudyGroupAsker studyGroupAsker = new StudyGroupAsker(userScanner);
        if (fileMode()) studyGroupAsker.setScriptMode();
        String name = studyGroupAsker.askQuestion("Do you want to change the StudyGroup's name?") ?
                studyGroupAsker.askStudyGroupName() : null;
        Coordinates coordinates = studyGroupAsker.askQuestion("Do you want to change the coordinates of the StudyGroup?") ?
                studyGroupAsker.askCoordinates() : null;
        Long studentsCount = studyGroupAsker.askQuestion("Do you want to change the StudyGroup's students count?") ?
                studyGroupAsker.askStudentsCount() : -1;
        long transferredStudents = studyGroupAsker.askQuestion("Do you want to change the number of transferred students in the StudyGroup?") ?
                studyGroupAsker.askTransferredStudents() : -1;
        Long averageMark = studyGroupAsker.askQuestion("Do you want to change the average mark of the StudyGroup?") ?
                studyGroupAsker.askAverageMark() : -1;
        Semester semesterEnum = studyGroupAsker.askQuestion("Do you want to change the semester of the StudyGroup?") ?
                studyGroupAsker.askSemester() : null;
        Person groupAdmin = studyGroupAsker.askQuestion("Do you want to change group admin of the StudyGroup?") ?
                studyGroupAsker.askPerson() : null;
        return new StudyGroup(
                name,
                coordinates,
                studentsCount,
                transferredStudents,
                averageMark,
                semesterEnum,
                groupAdmin
        );
    }

    /**
     * Checks if UserHandler is in file mode now.
     *
     * @return Is UserHandler in file mode now boolean.
     */
    private boolean fileMode() {
        return !scannerStack.isEmpty();
    }
}