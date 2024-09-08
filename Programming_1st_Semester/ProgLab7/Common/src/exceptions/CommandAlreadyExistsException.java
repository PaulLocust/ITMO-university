package exceptions;

public class CommandAlreadyExistsException extends Exception {
    public CommandAlreadyExistsException(String message) {
        super(message);
    }
}