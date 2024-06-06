package commands;

import commands.auxillary.AbstractCommand;
import commands.auxillary.Fillable;
import commands.dataBase.DataBase;
import exceptions.CommandAlreadyExistsException;
import exceptions.FilledIncorrect;
import exceptions.NotDatabaseUpdateException;
import exceptions.NotFoundCommandException;
import models.StudyGroup;
import models.StudyGroups;
import models.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Stream;

import static answers.ErrorAnswer.logger;

public final class CommandManager {
    private static CommandManager instance;
    private static StudyGroups studyGroups;
    private static ArrayList<AbstractCommand> commands;
    private static User user;
    private static DataBase database;

    public CommandManager() {
    }

    private CommandManager(String login, String password) throws ClassNotFoundException {
        database = new DataBase(login, password);
        studyGroups = new StudyGroups();
    }

    public static CommandManager getInstance(String login, String password) throws ClassNotFoundException {
        if (instance == null) {
            commands = new ArrayList<>();
            instance = new CommandManager(login,password);
        }

        return instance;
    }
    public static CommandManager getInstance() {
        if (instance == null) {
            commands = new ArrayList<>();
            instance = new CommandManager();
        }

        return instance;
    }

    public void initCommand(Class<? extends AbstractCommand> clazz, String name, String description, Class<?>... argsTypes) throws CommandAlreadyExistsException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (AbstractCommand command : commands) {
            if (command.getName().equals(name)) {
                throw new CommandAlreadyExistsException("A command named " + name + " already exists");
            }
        }

        AbstractCommand command = clazz.getConstructor(String.class, String.class, Class[].class)
                .newInstance(name, description, argsTypes);
        commands.add(command);
    }


    public static String[] parseArgs(String args) {
        String[] splitted = args.split(" ", 2);
        return splitted.length > 1 ? splitted[1].split(" ") : new String[]{};
    }
    public static String parseName(String command) {
        return command.split(" ")[0];
    }

    public static AbstractCommand getCommand(String name) throws NotFoundCommandException {
        for (AbstractCommand cmd : commands) {
            if (cmd.getName().equals(name)) {
                return cmd;
            }
        }
        throw new NotFoundCommandException("Command '" + name + "'was not initialized.");
    }
    public static void updateCollection() {
        try {
            HashSet<StudyGroup> updated = database.selectAllNotes();
            studyGroups.update(updated);
        } catch (SQLException | NotDatabaseUpdateException | FilledIncorrect e) {
            logger.severe( "Error with database. Try again later!");
        }
    }

    public static String getCommandsInfo() {
        StringBuilder builder = new StringBuilder();
        for (AbstractCommand cmd : commands) {
            builder.append(cmd.toString()).append("\n");
        }

        return builder.toString().trim();
    }

    public static User getUser() {
        return user;
    }

    public static String execute(User user, AbstractCommand command, Object[] args) {
        return command.execute(user, database, studyGroups, args);

    }

    public static String execute(User user,String commandName, Object[] args) throws NotFoundCommandException {
        AbstractCommand command = getCommand(commandName);
        return command.execute(user, database, studyGroups, args);
    }

    public static Object[] getFillableArgs(AbstractCommand command, Scanner scanner) {
        if (command instanceof Fillable) {
            return ((Fillable) command).fill(scanner);
        }
        return new Object[]{};
    }

    public static Object[] concatArgs(Object[] args, Object[] fillableArgs) {
        return Stream.concat(Arrays.stream(args), Arrays.stream(fillableArgs)).toArray(Object[]::new);
    }

    public static void validate(AbstractCommand command, Object[] args) {
        command.validate(args);
    }

    public static void validate(String commandName, Object[] args) throws NotFoundCommandException {
        AbstractCommand command = getCommand(commandName);
        command.validate(args);
    }
    public static void auth(User user) {
        CommandManager.user = user;
    }
}