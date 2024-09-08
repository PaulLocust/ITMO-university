package commands;


import commands.auxillary.AbstractCommand;
import commands.dataBase.DataBase;
import models.StudyGroups;
import models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** РАБОТАЕТ
 * Исполнение скрипта
 */
public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand(String name, String description, Class<?>[] argsTypes) {
        super(name, description, argsTypes);
    }

    public String execute(User user, DataBase db, StudyGroups studyGroups, Object... args) {
        try {
            validate(args);

            if (user != null) {
                File file = new File((String) args[0]);
                if (!file.exists()) return "The script does not exist";
                else if (file.exists() && !file.canRead()) return "The script cannot be read, check the file rights (read rights)";
                else if (file.exists() && !file.canExecute()) return "The script cannot be executed, check the file rights (execution rights)";
                else {
                    try {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine().trim();
                            List<String> collection = Arrays.asList(line.split(" "));
                            if (collection.get(0).equals("execute_script")) {
                                if (!studyGroups.isEmpty()) {
                                } else {
                                    return "The collection is empty, the recursion has been interrupted";
                                }
                            }
                        }
                        scanner.close();
                        return "The script was executed";
                    } catch (FileNotFoundException e) {
                        return "The script does not exist";
                    }
                }
            }
            return "You are not logged in";
        } catch (IllegalArgumentException e) {
            return "Error in arguments!";
        }

    }

    @Override
    public void validate(Object... args) throws IllegalArgumentException {
        super.validate(args);
    }
}