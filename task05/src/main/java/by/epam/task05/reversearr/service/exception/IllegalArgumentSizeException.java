package by.epam.task05.reversearr.service.exception;

public class IllegalArgumentSizeException extends Exception {
    public IllegalArgumentSizeException() {
        super();
    }

    public IllegalArgumentSizeException(String message) {
        super(message);
    }

    public IllegalArgumentSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentSizeException(Throwable cause) {
        super(cause);
    }

}
