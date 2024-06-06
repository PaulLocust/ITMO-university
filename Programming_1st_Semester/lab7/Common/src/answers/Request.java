package answers;

import commands.auxillary.AbstractCommand;
import models.User;
import java.io.Serializable;

public class Request implements Serializable {
    private AbstractCommand command;
    private Object[] args;
    private User user;

    public Request(User user,AbstractCommand command, Object[] args) {
        this.command = command;
        this.args = args;
        this.user=user;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public Object[] getArgs() {
        return args;
    }

    public User getUser() {
        return user;
    }
}