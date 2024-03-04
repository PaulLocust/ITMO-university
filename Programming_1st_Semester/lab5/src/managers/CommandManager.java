package managers;

import commands.auxillary.AbstractCommand;
import exceptions.HistoryIsEmptyException;
import java.util.*;

/**
 * Класс выполняющий функцию Invoker'а, вызывает команды
 */
public class CommandManager {

    private final ArrayList<AbstractCommand> commandsArrayList;
    private final int COMMAND_HISTORY_SIZE = 15;
    private final Queue<String> commandHistory = new ArrayDeque<>(COMMAND_HISTORY_SIZE);

    private final AbstractCommand help;
    private final AbstractCommand info;
    private final AbstractCommand show;
    private final AbstractCommand add;
    private final AbstractCommand updateById;
    private final AbstractCommand removeById;
    private final AbstractCommand clear;
    private final AbstractCommand save;
    private final AbstractCommand executeScript;
    private final AbstractCommand exit;
    private final AbstractCommand removeGreater;
    private final AbstractCommand removeLower;
    private final AbstractCommand history;
    private final AbstractCommand sumOfTransferredStudents;
    private final AbstractCommand filterStartsWithName;
    private final AbstractCommand filterLessThanStudentsCount;

    public CommandManager(AbstractCommand help, AbstractCommand info,
                          AbstractCommand show, AbstractCommand add, AbstractCommand updateById, AbstractCommand removeById,
                          AbstractCommand clear, AbstractCommand save, AbstractCommand executeScript, AbstractCommand exit,
                          AbstractCommand removeGreater, AbstractCommand removeLower, AbstractCommand history,
                          AbstractCommand sumOfTransferredStudents, AbstractCommand filterStartsWithName,
                          AbstractCommand filterLessThanStudentsCount) {
        this.help = help;
        this.info = info;
        this.show = show;
        this.add = add;
        this.updateById = updateById;
        this.removeById = removeById;
        this.clear = clear;
        this.save = save;
        this.executeScript = executeScript;
        this.exit = exit;
        this.removeGreater = removeGreater;
        this.removeLower = removeLower;
        this.history = history;
        this.sumOfTransferredStudents = sumOfTransferredStudents;
        this.filterStartsWithName = filterStartsWithName;
        this.filterLessThanStudentsCount = filterLessThanStudentsCount;

        commandsArrayList = new ArrayList<>(Arrays.asList(help, info, show, add, updateById, removeById, clear, save,
                executeScript, exit, removeGreater, removeLower, history, sumOfTransferredStudents, filterStartsWithName,
                filterLessThanStudentsCount));
    }

    /**
     * Если команда не найдена, напишет сообщение об этом
     * @param command команда, которая была не найдена
     * @return None
     */
    public boolean noSuchCommand(String command) {
        Console.printLn("Command '" + command + "' was not found. Try to write 'help' for more info.");
        return false;
    }

    /**
     * Выводит список всех команд и их описание
     * @param argument аргумент, переданный команде
     * @return Булево состояние команды
     */
    public boolean askHelp(String argument) {
        if (!help.execute(argument)) {
            for (AbstractCommand command : commandsArrayList) {
                Console.printLn(command.getName() + " - " + command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Выводит информацию о коллекции
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean giveInfo(String argument) {
        return info.execute(argument);
    }

    /**
     * Показать список элементов коллекции
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean showCollectionElements(String argument) {
        return show.execute(argument);
    }

    /**
     * Добавить элемент в коллекцию
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean addElement(String argument) {
        return add.execute(argument);
    }

    /**
     * Обновить элемент коллекции
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean updateElementById(String argument) {
        return updateById.execute(argument);
    }

    /**
     * Удалить элемент из коллекции
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean removeElementById(String argument) {
        return removeById.execute(argument);
    }

    /**
     * Очистить коллекцию
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean clearCollection (String argument) {
        return clear.execute(argument);
    }

    /**
     * Сохранить коллекцию в файл
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean saveCollection (String argument) {
        return save.execute(argument);
    }

    /**
     * Выполнить скрипт
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean executeScript(String argument) {
        return executeScript.execute(argument);
    }

    /**
     * Выйти из приложения
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean exit(String argument) {
        return exit.execute(argument);
    }

    /**
     * Удалить элементы, значение которых больше заданного
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean removeGreater(String argument) {
        return removeGreater.execute(argument);
    }

    /**
     * Удалить элементы, значение которых меньше заданного
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean removeLower(String argument) {
        return removeLower.execute(argument);
    }

    /**
     * Добавить команду в историю команд
     * @param commandToStore команда, которая будет храниться в очереди
     */
    public void addToHistory(String commandToStore) {
        for (AbstractCommand command : commandsArrayList) {
            if (command.getName().split(" ")[0].equals(commandToStore)) {
                if (commandHistory.size() < COMMAND_HISTORY_SIZE) {
                    commandHistory.offer(commandToStore); // Add to queue
                }
                else {
                    commandHistory.poll(); // Delete the oldest
                    commandHistory.offer(commandToStore);
                }

            }
        }
    }

    /**
     * Показать очередь, состоящую из последних написанных команд
     * @check COMMAND_HISTORY_SIZE
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean showCommandHistory(String argument) {
        if (!history.execute(argument)) {
            try {
                if (commandHistory.isEmpty()) throw new HistoryIsEmptyException();

                Console.printLn("Last used commands:");
                for (String s : commandHistory) {
                    if (s != null) Console.printLn(" " + s);
                }
                return true;
            } catch (HistoryIsEmptyException exception) {
                Console.printLn("You have not entered any command yet");
            }
        }
        return false;
    }

    /**
     * Вывести сумму переведённых студентов
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean showSumOfTransferredStudents(String argument) {
        return sumOfTransferredStudents.execute(argument);
    }

    /**
     * Вывести все элементы, чьё имя начинается с заданной подстроки
     * @param argument аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean showFilterStartsWithName(String argument) {
        return filterStartsWithName.execute(argument);
    }

    /**
     * Вывести все элементы, чьё поле studentsCount меньше заданного в аргументе
     * @param argument
     * @return ответ на правильное исполнение команды
     */
    public boolean showFilteredLessThanStudCount(String argument) {
        return filterLessThanStudentsCount.execute(argument);
    }


}
