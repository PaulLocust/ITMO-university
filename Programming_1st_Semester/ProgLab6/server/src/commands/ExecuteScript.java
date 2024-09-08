package commands;


import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import utility.ResponseOutputer;

/**
 * Исполнение скрипта
 */
public class ExecuteScript extends AbstractCommand {

    public ExecuteScript() {
        super("execute_script file_name", "<file_name>","Read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively.");
    }

    /**
     * Метод для выполнения скрипта из файла
     * @param stringArgument аргумент команды, введённой пользователем
     * @param objectArgument сериализованный объект класса StudyGroup, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            ResponseOutputer.appendLn("Execute script '" + stringArgument + "'...");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendLn("Usage: '" + getName() + " " + getUsage() + "'");
        }
        return false;
    }
}