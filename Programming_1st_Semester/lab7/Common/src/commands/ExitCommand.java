package commands;

import commands.auxillary.AbstractCommand;
import commands.dataBase.DataBase;
import models.StudyGroups;
import models.User;

/** РАБОТАЕТ
 * Выход из приложения
 */
public class ExitCommand extends AbstractCommand {
    public ExitCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            if (user != null) {
                System.exit(0);
                return "The program has been successfully completed!";
            }
            return "You are not logged in";
        } catch (IllegalArgumentException e) {
            return "Error in arguments!";
        }

    }
}