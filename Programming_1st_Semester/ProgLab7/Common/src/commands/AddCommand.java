package commands;

import commands.auxillary.AbstractCommand;
import commands.auxillary.Fillable;
import commands.dataBase.DataBase;
import exceptions.NotDatabaseUpdateException;
import exceptions.UserNotFoundException;
import models.StudyGroups;
import models.User;
import models.StudyGroup;
import java.sql.SQLException;
import java.util.Scanner;


/** РАБОТАЕТ
 * Этот класс нужен для добавления новых элементов в коллекцию
 */
public class AddCommand extends AbstractCommand implements Fillable {


    public AddCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase dataBase, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            if(user != null){
                int userID = dataBase.selectUserID(user.getLogin(), user.getPassword());
                System.out.println(userID);
                StudyGroup studyGroup = (StudyGroup) args[0];

                dataBase.insert(studyGroup, userID);
                CommandManager.updateCollection();
                return "\nItem added to collection";
            }
            return "You are not logged in";
        } catch (IllegalArgumentException e){
            return "Error in arguments!" + e.getMessage();
        } catch (SQLException | NotDatabaseUpdateException e){
            return "Error in database! Please try again later!";
        } catch (UserNotFoundException e){
            return "Error with user's data! Please auth again!";
        }
    }

    @Override
    public Object[] fill(Scanner scanner) {
        Object[] args = new Object[1];
        args[0] = StudyGroup.fillStudyGroup(scanner);
        return args;
    }

}