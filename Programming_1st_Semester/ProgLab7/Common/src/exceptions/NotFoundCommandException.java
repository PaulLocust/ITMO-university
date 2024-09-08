package exceptions;

public class NotFoundCommandException extends Exception {
    public NotFoundCommandException(String message) {
        super(message);
    }
}
