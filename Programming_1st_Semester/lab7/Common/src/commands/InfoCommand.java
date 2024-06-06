package commands;

import commands.auxillary.AbstractCommand;
import commands.dataBase.DataBase;
import models.StudyGroups;
import models.User;

/** РАБОТАЕТ
 * Класс для получения информации о коллекции
 */
public class InfoCommand extends AbstractCommand {
    public InfoCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {

            validate(args);
            if (user != null) {
                return studyGroups.toString();
            }
            return "You are not logged in";
        } catch (IllegalArgumentException e) {
            return "Error in arguments!";
        }

    }
}