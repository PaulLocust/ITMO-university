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
import java.util.Iterator;

/** РАБОТАЕТ
 * Класс, удаляющий все элементы коллекции, поле studentsCount которых больше значения представленного аргумента.
 * Происходит сравнение аргумента и поля studentsCount
 */
public class RemoveGreaterCommand extends AbstractCommand {

    public RemoveGreaterCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    @Override
    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);
            if (user != null) {
                if (args.length == 0 || args[0] == null || !(args[0] instanceof String)) {
                    return "Error: No target element provided or incorrect argument type.";
                }
                try {
                    int studentsCount = Integer.parseInt((String) args[0]);
                    Iterator<StudyGroup> iterator = studyGroups.iterator();
                    boolean removed = false;

                    while (iterator.hasNext()) {
                        StudyGroup p = iterator.next();
                        if (p.getStudentsCount() > studentsCount) {
                            try {
                                StudyGroup studyGroup = db.selectStudyGroup(p.getId());
                                if (user.getLogin().equals(studyGroup.getOwner().getLogin())) {
                                    db.deleteNote(studyGroup.getId());
                                    iterator.remove(); // Удаляем из коллекции
                                    removed = true;
                                }
                            } catch (StudyGroupNotFound | SQLException e) {
                                return "Error: " + e.getMessage();
                            }
                        }
                    }

                    if (removed) {
                        CommandManager.updateCollection();
                        return "Operation successful, objects removed.";
                    } else {
                        return "No objects removed. Either no objects matched the criteria or you are not the owner.";
                    }
                } catch (NumberFormatException e) {
                    return "Error: studentsCount should be a valid integer.";
                }
            } else {
                return "You are not logged in!";
            }
        } catch (IllegalArgumentException e) {
            return "Error in arguments!";
        }
    }

}
