package commands.concreteCommands;

import commands.auxillary.AbstractCommand;
import exceptions.WrongAmountOfElementsException;
import managers.Console;

/**
 * Класс для показа истории ввода команд
 */
public class History extends AbstractCommand {


    public History() {
        super("history", "Print the last 15 commands (without their arguments).");
    }

    /**
     * Метод для показа истории последних 15 команд, введённых в консоль приложения
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
