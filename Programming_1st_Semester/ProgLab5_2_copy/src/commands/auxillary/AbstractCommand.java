package commands.auxillary;


/**
 * The AbstractCommand class is an abstract class that implements the Command interface.
 *
 */
public abstract class AbstractCommand implements Command{

    private final String name;
    private final String description;

    public AbstractCommand(String name, String description) {
        this.name = name;
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
     * Метод для получения описания команды
     * @return command description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Базовый абстрактный метод выполнения команды
     * @param argument аргумент команды, введённой пользователем
     * @see Command
     */
    @Override
    public abstract boolean execute(String argument);

}
