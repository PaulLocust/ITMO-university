package commands;

import commands.auxillary.AbstractCommand;
import commands.dataBase.DataBase;
import models.StudyGroups;
import models.User;

/**
 * Сохранение коллекции в файл
 */
public class SaveCommand extends AbstractCommand {
    public SaveCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            return "The collection is saved";
        } catch (IllegalArgumentException e) {
            return "Error in arguments!";
        }
    }
}