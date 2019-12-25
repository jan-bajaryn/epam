package by.epam.bus.repos.exception;

public class IllegalMillageInputException extends Exception {
    public IllegalMillageInputException() {
    }

    public IllegalMillageInputException(String message) {
        super(message);
    }

    public IllegalMillageInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalMillageInputException(Throwable cause) {
        super(cause);
    }

    public IllegalMillageInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
