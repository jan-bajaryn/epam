package by.epam.bus.factory.exception;

public class IllegalPersonParamsException extends Exception {
    public IllegalPersonParamsException() {
    }

    public IllegalPersonParamsException(String message) {
        super(message);
    }

    public IllegalPersonParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPersonParamsException(Throwable cause) {
        super(cause);
    }

    public IllegalPersonParamsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
