
import Reader.CommandInitializer;
import answers.Request;
import commands.CommandManager;
import commands.auxillary.AbstractCommand;
import exceptions.CommandAlreadyExistsException;
import exceptions.NotFoundCommandException;
import models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;


public class CommandReader {
    private final Sender sender;
    private User user;
    private Set<String> executedScripts = new HashSet<>(); //добавлено

    public CommandReader(Sender sender) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, CommandAlreadyExistsException {
        this.sender = sender;
        CommandManager manager = new CommandInitializer().initCommands(CommandManager.getInstance());
    }

    public void read() {
        System.out.println("The program has been successfully launched. Enter 'help' to find out the list of commands");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String line = scanner.nextLine().trim();
                if(line.equals("")) continue;
                readCommand(line, scanner);
                System.out.println();
            }catch (NoSuchElementException e){
                PrintConsole.printerror("Well, why-you decided to Ctrl-D us :(");
                System.exit(0);
            }
            catch (NotFoundCommandException | IllegalArgumentException e) {
                PrintConsole.printerror("There is no such command");
            } catch (ArrayIndexOutOfBoundsException e) {
                PrintConsole.printerror("You didn't specify an argument");
            }
        }
    }

    private void readCommand(String line, Scanner scanner) throws NotFoundCommandException {
        String name = CommandManager.parseName(line);

        switch (name) {
            case "exit" -> System.exit(0);
            case "execute_script" -> {
                Object[] args = CommandManager.parseArgs(line);
                File file = new File((String) args[0]);
                String filePath = file.getAbsolutePath();
                if (!file.exists())
                    System.err.println("The script does not exist");
                else if (file.exists() && !file.canRead())
                    System.err.println("The script cannot be read, check the file rights (rights to read)");
                else if (file.exists() && !file.canExecute())
                    System.err.println("The script cannot be executed, check the file rights (right to execute)");
                else {
                    if (executedScripts.contains(filePath)) {
                        System.err.println("Recursion detected: " + filePath);
                        return;
                    }

                    executedScripts.add(filePath);

                    try {
                        Scanner fileScanner = new Scanner(file);
                        while (fileScanner.hasNextLine()) {
                            String fileLine = fileScanner.nextLine().trim();
                            readCommand(fileLine, fileScanner);
                        }
                        fileScanner.close();
                    } catch (FileNotFoundException e) {
                        PrintConsole.printerror("The script does not exist");
                    }
                }

            }
            case "auth" -> {
                Object[] args = CommandManager.parseArgs(line);
                AbstractCommand command = CommandManager.getCommand(name);
                Object[] fillableArg = CommandManager.getFillableArgs(command, scanner);
                args = CommandManager.concatArgs(args, fillableArg);
                CommandManager.validate(command, args);
                user = (User) args[0];
                sender.send(new Request(user, command, args));
            }
            default -> {
                Object[] args = CommandManager.parseArgs(line);
                AbstractCommand command = CommandManager.getCommand(name);
                Object[] fillableArg = CommandManager.getFillableArgs(command, scanner);
                args = CommandManager.concatArgs(args, fillableArg);

                CommandManager.validate(command, args);
                sender.send(new Request(user, command, args));
            }
        }
    }
}