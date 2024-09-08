package commands.auxillary;

public interface Command {
    /**
     Базовый метод для выполнения команды
     */
    boolean execute(String commandStringArgument, Object commandObjectArgument);

    /**
     * Базовый метод для показа имени команды
     *
     * @return command name
     */
    String getName();

    /**
     * Базовый метод для показа описания команды
     *
     * @return command description
     */
    String getDescription();

    /**
     * Базовый метод для показа необходимых аргументов для выполнения команды
     *
     * @return command usage
     */
    String getUsage();

}