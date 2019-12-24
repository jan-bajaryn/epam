package by.epam.bus.parser;

public class IllegalInputCountException extends Exception {
    public IllegalInputCountException() {
        super();
    }

    public IllegalInputCountException(String message) {
        super(message);
    }

    public IllegalInputCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputCountException(Throwable cause) {
        super(cause);
    }

    protected IllegalInputCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
