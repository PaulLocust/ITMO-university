package commands;

import commands.auxillary.AbstractCommand;
import commands.auxillary.Fillable;
import commands.dataBase.DataBase;
import exceptions.NotDatabaseUpdateException;
import exceptions.StudyGroupNotFound;
import models.*;
import java.sql.SQLException;
import java.util.Scanner;

/** РАБОТАЕТ
 * Класс, который нужен для обновления какого-либо элемента коллекции
 */
public class UpdateCommand extends AbstractCommand implements Fillable {

    public UpdateCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            if (user != null) {
                if (studyGroups.isEmpty()) {
                    return "The command cannot be executed because the collection is empty. " +
                            "Add items to the collection using the add command";
                } else {
                    int id = Integer.parseInt((String) args[0]);
                    StudyGroup studyGroup = (StudyGroup) args[1];
                    try {
                        StudyGroup dbOrganization = db.selectStudyGroup(id);
                        if (user.getLogin().equals(dbOrganization.getOwner().getLogin())) {
                            db.update(id, studyGroup);
                            CommandManager.updateCollection();
                            return "The object was successfully updated";
                        }
                        return "You do not have the rights to change this object";
                    } catch (StudyGroupNotFound | SQLException | NotDatabaseUpdateException organizationNotFound) {
                        organizationNotFound.printStackTrace();
                    }
                }
            }
            return "You are not logged in";
        } catch (IllegalArgumentException e) {
            return "Error in arguments!";
        }
    }

    @Override
    public Object[] fill(Scanner scanner) {
        Object[] args = new Object[1];
        args[0] = StudyGroup.fillStudyGroup(scanner);
        return args;
    }
}
