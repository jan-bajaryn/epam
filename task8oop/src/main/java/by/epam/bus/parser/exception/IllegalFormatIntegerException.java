package by.epam.bus.parser.exception;

public class IllegalFormatIntegerException extends Exception {
    public IllegalFormatIntegerException() {
    }

    public IllegalFormatIntegerException(String message) {
        super(message);
    }

    public IllegalFormatIntegerException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFormatIntegerException(Throwable cause) {
        super(cause);
    }

    public IllegalFormatIntegerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
