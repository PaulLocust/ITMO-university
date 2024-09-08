package exceptions;

public class NotDatabaseUpdateException extends Exception {
    public NotDatabaseUpdateException(String message) {
        super(message);
    }
}
