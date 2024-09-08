package commands;


import commands.auxillary.AbstractCommand;
import commands.dataBase.DataBase;
import models.StudyGroup;
import models.StudyGroups;
import models.User;


/** РАБОТАЕТ
 * Класс, убирающий элемент коллекции по его id
 */
public class RemoveByIdCommand extends AbstractCommand {
    public RemoveByIdCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            if (user != null) {
                int id = Integer.parseInt((String) args[0]);
                try {
                    StudyGroup studyGroup = db.selectStudyGroup(id);
                    if (user.getLogin().equals(studyGroup.getOwner().getLogin())) {
                        db.deleteNote(id);
                        CommandManager.updateCollection();
                        return "The object was successfully deleted";
                    }

                    return "You don't have the rights to delete this object";
                } catch (Exception e) {
                    return "You entered the data incorrectly.";
                }
            }
            return "You are not logged in";
        } catch (IllegalArgumentException e) {
            return "Error in arguments!";
        }

    }
}