package myExceptionPackage;

public class OverloadedTableException extends Exception{
    private final String errorMessage;
    public OverloadedTableException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    @Override
    public String getMessage(){
        return "\nОбъект: " + errorMessage + "\n";
    }
}
