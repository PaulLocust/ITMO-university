package commands;



import commands.auxillary.AbstractCommand;
import commands.dataBase.DataBase;
import exceptions.EmptyCollectionException;
import exceptions.StudyGroupNotFound;
import exceptions.WrongAmountOfElementsException;
import models.StudyGroup;
import models.StudyGroups;
import models.User;


import java.sql.SQLException;
import java.util.HashSet;

/**
 * РАБОТАЕТ.
 * Этот класс показывает сумму переведённых студентов из каждого элемента коллекции
 */
public class SumOfTransferredStudentsCommand extends AbstractCommand {
    public SumOfTransferredStudentsCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            if (user != null) {
                int transferredSum = 0;
                for (StudyGroup p : studyGroups) {
                    try {
                        StudyGroup studyGroup = db.selectStudyGroup(p.getId());
                        transferredSum += studyGroup.getTransferredStudents();
                    } catch (StudyGroupNotFound | SQLException e) {
                        return "Error: " + e.getMessage();
                    }
                }
                return "Operation successful, sum of transferred students: " + transferredSum;
            } else {
                return "You are not logged in!";
            }
        } catch (IllegalArgumentException e) {
            return "Error in arguments!";
        }
    }
}