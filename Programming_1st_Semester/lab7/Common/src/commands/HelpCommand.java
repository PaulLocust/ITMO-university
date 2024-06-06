package commands;


import commands.auxillary.AbstractCommand;
import commands.dataBase.DataBase;
import models.StudyGroups;
import models.User;

/** РАБОТАЕТ
 * Этот класс показывает справку по всем доступным командам приложения
 */
public class HelpCommand extends AbstractCommand {
    public HelpCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            return CommandManager.getCommandsInfo();
        } catch (IllegalArgumentException e) {
            return "Error in arguments!";
        }
    }
}