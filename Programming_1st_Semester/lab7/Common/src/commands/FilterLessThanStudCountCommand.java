package commands;

import commands.auxillary.AbstractCommand;
import commands.dataBase.DataBase;
import models.StudyGroup;
import models.StudyGroups;
import models.User;



import java.util.HashSet;

/** РАБОТАЕТ.
 * Этот класс фильтрует элементы коллекции по значению поля studentsCount. То есть необходимо вывести все элементы
 * коллекции, поле studentsCount которых, меньше заданного аргумента команды
 */
public class FilterLessThanStudCountCommand extends AbstractCommand {
    public FilterLessThanStudCountCommand(String name, String description, Class<?>[] argsTypes) {
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

                int studentsCount;
                try {
                    studentsCount = Integer.parseInt((String) args[0]);
                } catch (NumberFormatException e) {
                    return "Error: studentsCount should be a valid integer.";
                }

                if (studyGroups.isEmpty()) {
                    return "The collection is empty. There is no data inside the file!";
                } else {
                    HashSet<StudyGroup> result = new HashSet<>();
                    for (StudyGroup p : studyGroups) {
                        if (p.getStudentsCount() < studentsCount) {
                            try {
                                StudyGroup studyGroup = db.selectStudyGroup(p.getId());
                                result.add(studyGroup);
                            } catch (Exception e) {
                                return "Error: " + e.getMessage();
                            }
                        }
                    }

                    if (result.isEmpty()) {
                        return "No study groups found with studentsCount less than " + studentsCount;
                    }

                    StringBuilder builder = new StringBuilder();
                    for (StudyGroup sg : result) {
                        builder.append(sg).append("\n");
                    }
                    return builder.toString();
                }
            }
            return "You are not logged in";
        } catch (Exception e) {
            return "Error in printing! Please contact administrator!";
        }
    }


}