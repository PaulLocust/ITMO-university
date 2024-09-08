package utility;


import commands.auxillary.AbstractCommand;
import exceptions.HistoryIsEmptyException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

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
    private final AbstractCommand serverExitCommand;

    public CommandManager(AbstractCommand help, AbstractCommand info,
                          AbstractCommand show, AbstractCommand add, AbstractCommand updateById, AbstractCommand removeById,
                          AbstractCommand clear, AbstractCommand save, AbstractCommand executeScript, AbstractCommand exit,
                          AbstractCommand removeGreater, AbstractCommand removeLower, AbstractCommand history,
                          AbstractCommand sumOfTransferredStudents, AbstractCommand filterStartsWithName,
                          AbstractCommand filterLessThanStudentsCount, AbstractCommand serverExitCommand) {
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
        this.serverExitCommand = serverExitCommand;

        commandsArrayList = new ArrayList<>(Arrays.asList(help, info, show, add, updateById, removeById, clear, save,
                executeScript, exit, removeGreater, removeLower, history, sumOfTransferredStudents, filterStartsWithName,
                filterLessThanStudentsCount, serverExitCommand));
    }

    /**
     * Если команда не найдена, напишет сообщение об этом
     * @param command команда, которая была не найдена
     * @return None
     */
    public boolean noSuchCommand(String command) {
        Outputer.printLn("Command '" + command + "' was not found. Try to write 'help' for more info.");
        return false;
    }

    /**
     * Выводит список всех команд и их описание
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return Булево состояние команды
     */
    public boolean askHelp(String stringArgument, Object objectArgument) {
        if (help.execute(stringArgument, objectArgument)) {
            for (AbstractCommand command : commandsArrayList) {
                ResponseOutputer.appendTable(command.getName() + " " + command.getUsage(), command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Выводит информацию о коллекции
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean giveInfo(String stringArgument, Object objectArgument) {
        return info.execute(stringArgument, objectArgument);
    }

    /**
     * Показать список элементов коллекции
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean showCollectionElements(String stringArgument, Object objectArgument) {
        return show.execute(stringArgument, objectArgument);
    }

    /**
     * Добавить элемент в коллекцию
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean addElement(String stringArgument, Object objectArgument) {
        return add.execute(stringArgument, objectArgument);
    }

    /**
     * Обновить элемент коллекции
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean updateElementById(String stringArgument, Object objectArgument) {
        return updateById.execute(stringArgument, objectArgument);
    }

    /**
     * Удалить элемент из коллекции
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean removeElementById(String stringArgument, Object objectArgument) {
        return removeById.execute(stringArgument, objectArgument);
    }

    /**
     * Очистить коллекцию
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean clearCollection (String stringArgument, Object objectArgument) {
        return clear.execute(stringArgument, objectArgument);
    }

    /**
     * Сохранить коллекцию в файл
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean saveCollection (String stringArgument, Object objectArgument) {
        return save.execute(stringArgument, objectArgument);
    }

    /**
     * Выполнить скрипт
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean executeScript(String stringArgument, Object objectArgument) {
        return executeScript.execute(stringArgument, objectArgument);
    }

    /**
     * Выйти из приложения
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean serverExit(String stringArgument, Object objectArgument) {
        saveCollection(stringArgument, objectArgument);
        return serverExitCommand.execute(stringArgument, objectArgument);
    }

    /**
     * Удалить элементы, значение которых больше заданного
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean removeGreater(String stringArgument, Object objectArgument) {
        return removeGreater.execute(stringArgument, objectArgument);
    }

    /**
     * Удалить элементы, значение которых меньше заданного
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean removeLower(String stringArgument, Object objectArgument) {
        return removeLower.execute(stringArgument, objectArgument);
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
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean showCommandHistory(String stringArgument, Object objectArgument) {
        if (!history.execute(stringArgument, objectArgument)) {
            try {
                if (commandHistory.isEmpty()) throw new HistoryIsEmptyException();

                Outputer.printLn("Last used commands:");
                for (String s : commandHistory) {
                    if (s != null) Outputer.printLn(" " + s);
                }
                return true;
            } catch (HistoryIsEmptyException exception) {
                ResponseOutputer.appendLn("You have not entered any command yet");
            }
        }
        return false;
    }

    /**
     * Вывести сумму переведённых студентов
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean showSumOfTransferredStudents(String stringArgument, Object objectArgument) {
        return sumOfTransferredStudents.execute(stringArgument, objectArgument);
    }

    /**
     * Вывести все элементы, чьё имя начинается с заданной подстроки
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean showFilterStartsWithName(String stringArgument, Object objectArgument) {
        return filterStartsWithName.execute(stringArgument, objectArgument);
    }

    /**
     * Вывести все элементы, чьё поле studentsCount меньше заданного в аргументе
     * @param stringArgument строковый аргумент, переданный команде
     * @param objectArgument объектный аргумент, переданный команде
     * @return ответ на правильное исполнение команды
     */
    public boolean showFilteredLessThanStudCount(String stringArgument, Object objectArgument) {
        return filterLessThanStudentsCount.execute(stringArgument, objectArgument);
    }


}
