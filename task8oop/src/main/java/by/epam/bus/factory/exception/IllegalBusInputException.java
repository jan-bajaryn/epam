package by.epam.bus.factory.exception;

public class IllegalBusInputException extends Exception {
    public IllegalBusInputException() {
    }

    public IllegalBusInputException(String message) {
        super(message);
    }

    public IllegalBusInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalBusInputException(Throwable cause) {
        super(cause);
    }

    public IllegalBusInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
