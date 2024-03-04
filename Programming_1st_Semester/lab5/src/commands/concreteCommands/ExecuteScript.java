package commands.concreteCommands;

import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import managers.Console;

/**
 * Исполнение скрипта
 */
public class ExecuteScript extends AbstractCommand {

    public ExecuteScript() {
        super("execute_script file_name", "Read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively.");
    }

    /**
     * Метод для выполнения скрипта из файла
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.printLn("Execute script '" + argument + "'...");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.printLn("Usage of: '" + getName() + "'");
        }
        return false;
    }


}
