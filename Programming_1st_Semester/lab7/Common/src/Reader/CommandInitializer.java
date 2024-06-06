package Reader;



import commands.*;
import exceptions.CommandAlreadyExistsException;
import models.StudyGroup;
import models.User;

import java.lang.reflect.InvocationTargetException;

public class CommandInitializer {
    public static CommandManager initCommands(CommandManager manager) throws CommandAlreadyExistsException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        manager.initCommand(HelpCommand.class , "help", "Displays help for commands");
        manager.initCommand(InfoCommand.class, "info", "Display information about the collection");
        manager.initCommand(ShowCommand.class , "show", "Outputs all elements of the collection");
        manager.initCommand(AddCommand.class , "add", "Adds an item to the collection", StudyGroup.class );
        manager.initCommand(UpdateCommand.class, "update", "Updates the value of the element by id", String.class, StudyGroup.class);
        manager.initCommand(RemoveByIdCommand.class, "remove_by_id", "Deletes an element with the specified id", String.class);
        manager.initCommand(ClearCommand.class, "clear", "Clears the collection");
        manager.initCommand(ExecuteScriptCommand.class, "execute_script", "Reads and executes a script from a file", String.class);
        manager.initCommand(ExitCommand.class, "exit", "Exit, without saving");

        manager.initCommand(RemoveGreaterCommand.class, "remove_greater", "Remove from the collection all elements greater than a given value.", String.class); //дописать?
        manager.initCommand(RemoveLowerCommand.class, "remove_lower", "Remove from a collection all elements smaller than a given value.", String.class); //дописать и 3 нижних?
        manager.initCommand(SumOfTransferredStudentsCommand.class, "sum_of_transferred_students", "Display the sum of the values of the transferredStudents field for all elements of the collection");
        manager.initCommand(FilterStartsNameCommand.class, "filter_starts_with_name", "Print elements whose name field value begins with a given substring.", String.class);
        manager.initCommand(FilterLessThanStudCountCommand.class, "filter_less_than_students_count", "Display elements whose studentsCount field value is less than the specified one.", String.class);

        manager.initCommand(AuthCommand.class, "auth", "Authorizes the user", User.class);
        manager.initCommand(RegisterCommand.class, "register", "Registers a user", User.class);
        return manager;
    }
}