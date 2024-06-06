package commands.auxillary;
import commands.dataBase.DataBase;
import models.StudyGroup;
import models.StudyGroups;
import models.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractCommand implements Serializable {
    protected static ArrayList<AbstractCommand> commands = new ArrayList<>();
    protected StudyGroups studyGroups;
    private String name;
    private final String description;
    protected Class<?>[] argsTypes;

    public AbstractCommand(String name, String description, Class<?>[] argsTypes) {
        this.name = name;
        this.description = description;
        this.argsTypes = argsTypes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSignature() {
        return name + " " + Arrays.toString(Arrays.stream(argsTypes)
                        .map((arg) -> arg.getName().split("\\."))
                        .map((arg) -> arg[arg.length - 1]).toArray())
                .replace('[', '{')
                .replace(']', '}');
    }

    public void validate(Object... args) throws IllegalArgumentException {
        try {
            if(args.length == argsTypes.length){
                for (int i = 0; i < args.length; i++){
                    if(args[i].getClass() != argsTypes[i]){
                        throw new IllegalArgumentException("Invalid parameter type specified - " + getSignature());
                    }
                }
            } else throw new IllegalArgumentException();
        } catch (IllegalArgumentException e){
            System.out.println("The number of parameters does not correspond to the number of parameters of the command - " + getSignature());
        }
    }

    @Override
    public String toString() {
        return getSignature() + " - " + description;
    }

    public abstract String execute(User user, DataBase db, StudyGroups studyGroups, Object... args);
}