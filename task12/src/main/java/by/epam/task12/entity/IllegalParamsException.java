package by.epam.task12.entity;

public class IllegalParamsException extends RuntimeException {
    public IllegalParamsException() {
    }

    public IllegalParamsException(String message) {
        super(message);
    }

    public IllegalParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParamsException(Throwable cause) {
        super(cause);
    }
}
