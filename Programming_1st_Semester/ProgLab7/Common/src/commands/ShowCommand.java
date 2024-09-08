package commands;

import commands.auxillary.AbstractCommand;
import commands.dataBase.DataBase;
import models.StudyGroups;
import models.User;

/** РАБОТАЕТ
 * Класс, показывающий состояние всех полей каждого из элементов коллекции
 */
public class ShowCommand extends AbstractCommand {
    public ShowCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            if (user != null) {
                if (studyGroups.isEmpty()) {
                    return "The collection is empty. There is no data inside the file!";
                } else {
                    Object[] out = studyGroups.toArray();
                    StringBuilder builder = new StringBuilder();
                    for (Object o : out) {
                        builder.append(o).append("\n");
                    }
                    return builder.toString();

                }
            }
            return "You are not logged in";
        } catch (IllegalArgumentException e) {
            return "Error in arguments!";
        }

    }
}