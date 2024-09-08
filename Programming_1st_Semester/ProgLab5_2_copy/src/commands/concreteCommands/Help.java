package commands.concreteCommands;

import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import managers.Console;

/**
 * Этот класс показывает справку по всем доступным командам приложения
 */
public class Help extends AbstractCommand {

    public Help() {
        super("help", "Shows reference about available commands.");

    }

    /**
     * Метод для показа справки по командам
     * @param argument аргумент команды, введённой пользователем
     * @return ответ на правильное исполнение команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
        } catch (WrongAmountOfElementsException exception) {
            Console.printLn("Usage: '" + getName() + "'");
        }
        return false;

    }

}