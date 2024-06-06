package commands;


import commands.auxillary.AbstractCommand;
import commands.dataBase.DataBase;
import exceptions.UserNotFoundException;
import models.StudyGroups;
import models.User;

import java.sql.SQLException;


/** РАБОТАЕТ
 * Очистка коллекции
 */
public class ClearCommand extends AbstractCommand {
    public ClearCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            if (user!= null){
                int userID = db.selectUserID(user.getLogin(), user.getPassword());
                db.deleteUserNotes(userID);
                CommandManager.updateCollection();
                return "The collection has been cleared";
            }
            return "You are not logged in";
        } catch (IllegalArgumentException e){
            return "Error in arguments!";
        } catch (SQLException e){
            return "Error in database! Please try again later!";
        } catch (UserNotFoundException e){
            return "Error with user's data! Please auth again!";
        }

    }
}

