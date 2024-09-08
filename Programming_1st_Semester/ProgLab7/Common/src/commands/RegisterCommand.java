package commands;

import commands.auxillary.AbstractCommand;
import commands.auxillary.Fillable;
import commands.dataBase.DataBase;
import exceptions.NotDatabaseUpdateException;
import models.StudyGroups;
import models.User;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * РАБОТАЕТ
 */
public class RegisterCommand extends AbstractCommand implements Fillable {
    public RegisterCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            User regUser = (User) args[0];
            db.insert(regUser);
            return "User " + regUser.getLogin() + " successfully registered";
        } catch (SQLException | NotDatabaseUpdateException e) {
            return "Error in database! Please try again later!";
        }
    }

    @Override
    public Object[] fill(Scanner scanner) {
        Object[] args = new Object[1];
        args[0] = User.fillUser(scanner);
        return args;
    }
}
