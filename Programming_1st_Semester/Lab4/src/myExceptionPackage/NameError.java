package myExceptionPackage;

public class NameError extends RuntimeException{
    public NameError(String errorMessage){
        super(errorMessage);
    }
}