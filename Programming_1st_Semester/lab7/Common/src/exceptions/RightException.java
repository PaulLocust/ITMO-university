package exceptions;

public class RightException extends Exception{
    public RightException(String arg){
        System.err.println(arg);
    }
}
