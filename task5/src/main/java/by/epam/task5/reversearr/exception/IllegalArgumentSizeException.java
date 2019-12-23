package by.epam.task5.reversearr.exception;

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

    protected IllegalArgumentSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
