package managers;

import exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Console - это класс позволяющий взаимодействовать с консолью приложения и писать разные вещи
 */
public class Console {

    public static final String PS1 = "$ ";
    public static final String PS2 = "> ";

    private final CommandManager commandManager;
    private final Scanner userScanner;
    private final StudyGroupAsker studyGroupAsker;
    private final List<String> scriptStack = new ArrayList<>();

    public Console(CommandManager commandManager, Scanner userScanner, StudyGroupAsker studyGroupAsker) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.studyGroupAsker = studyGroupAsker;

    }

    /**
     * Этот метод нужен для выполнения скриптов
     * @param argument Имя файла, в котором лежит скрипт
     * @return выводимое значение представляет собой число, которое говорит нам о статусе выполнения команды
     */
    public int scriptMode(String argument) {
        String[] userCommand;
        int commandStatus;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = studyGroupAsker.getUserScanner();
            studyGroupAsker.setUserScanner(scriptScanner);
            studyGroupAsker.setScriptMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.printLn(Console.PS1 + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            studyGroupAsker.setUserScanner(tmpScanner);
            studyGroupAsker.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.printLn("Check script for correct input data!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printError("File was not found!");
        } catch (NoSuchElementException exception) {
            Console.printError("Script file is empty!");
        } catch (ScriptRecursionException exception) {
            Console.printError("Scripts can't be recursive!");
        } catch (IllegalStateException exception) {
            Console.printError("Unexpected error!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size()-1);
        }
        return 1;
    }

    /**
     * Этот метод представляет собой интерактивный режим программы. Он будет просить пользователя ввести что-нибудь,
     * до тех пор, пока не будет написана команда "exit"
     */
    public void interactiveMode() {
        String[] userCommand;
        int commandStatus;
        try {
            do {
                Console.print(PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandManager.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            Console.printError("User input not found!");
        } catch (IllegalStateException exception) {
            Console.printError("Unexpected error!");
        }
    }

    /**
     * Печатает полученный объект в стандартный поток вывода
     * @param toOut объект для печати
     */
    public static void print(Object toOut){
        System.out.print(toOut);
    }

    /**
     * Печатает полученный объект в консоль
     * @param toOut объект для печати
     */
    public static void printLn(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Печатает сообщение об ошибке в консоль
     * @param toOut объект для печати
     */
    public static void printError(Object toOut) {
        System.out.println("Error: " + toOut);
    }

    /**
     * Функция принимает массив String в качестве аргумента и проверяет первый элемент массива.
     * Если первый элемент массива пуст, то метод ничего не делает.
     * @param userCommand команда, введённая пользователем
     * @return число, которое может быть 0, 1 или 2
     */
    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.askHelp(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandManager.giveInfo(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.showCollectionElements(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandManager.addElement(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandManager.updateElementById(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.removeElementById(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clearCollection(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandManager.saveCollection(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "remove_greater":
                if (!commandManager.removeGreater(userCommand[1])) return 1;
                break;
            case "remove_lower":
                if (!commandManager.removeLower(userCommand[1])) return 1;
                break;
            case "history":
                if (!commandManager.showCommandHistory(userCommand[1])) return 1;
                break;
            case "sum_of_transferred_students":
                if (!commandManager.showSumOfTransferredStudents(userCommand[1])) return 1;
                break;
            case "filter_starts_with_name":
                if (!commandManager.showFilterStartsWithName(userCommand[1])) return 1;
                break;
            case "filter_less_than_students_count":
                if (!commandManager.showFilteredLessThanStudCount(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commandManager.exit(userCommand[1])) return 1;
                else return 2;
            default:
                if (!commandManager.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }


}
