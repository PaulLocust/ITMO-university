package commands.concreteCommands;

import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import managers.Console;

/**
 * Выход из приложения
 */
public class Exit extends AbstractCommand {

    public Exit() {
        //super("exit", "End the program (without saving to a file).");
        super("exit", "End the program (without saving to a file).");
    }

    /**
     * Метод для выхода из приложения
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.printLn("Exit");
            System.exit(0);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.printLn("Использование: '" + getName() + "'");
        }
        return false;
    }

}
