package error_checking;

public class UnrecognizableInputException extends RuntimeException {
    public UnrecognizableInputException() {}
    public UnrecognizableInputException(String message) { super(message); }
}
