package commands;

import commands.auxillary.AbstractCommand;
import commands.auxillary.Fillable;
import commands.dataBase.DataBase;
import exceptions.UserNotFoundException;
import models.StudyGroups;
import models.User;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * РАБОТАЕТ
 */
public class AuthCommand extends AbstractCommand implements Fillable {
    public AuthCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            User authUser = (User)args[0];
            db.selectUserID(authUser.getLogin(), authUser.getPassword());
            CommandManager.auth(authUser);
            return "You have successfully logged in as " + authUser.getLogin();
        } catch (SQLException e) {
            return "Error in database! Please try again later!";
        } catch (UserNotFoundException e){
            return "Error with user's data! Please auth again!";
        }
    }

    @Override
    public Object[] fill(Scanner scanner) {
        Object[] args = new Object[1];
        args[0] = User.fillUser(scanner);
        return args;
    }
}
