package commands.auxillary;

/**
 * The AbstractCommand class is an abstract class that implements the Command interface.
 *
 */
public abstract class AbstractCommand implements Command{

    private final String name;
    private final String usage;
    private final String description;

    public AbstractCommand(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    /**
     * Метод для получения имени команды
     * @return command name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Метод для получения использования команды
     * @return command usage
     */
    @Override
    public String getUsage() {
        return usage;
    }

    /**
     * Метод для получения описания команды
     * @return command description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Базовый абстрактный метод выполнения команды
     * @param commandStringArgument аргумент команды, введённой пользователем
     * @see Command
     */
    @Override
    public abstract boolean execute(String commandStringArgument, Object commandObjectArgument);

}