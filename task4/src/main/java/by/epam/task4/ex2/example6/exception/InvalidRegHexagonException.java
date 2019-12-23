package by.epam.task4.ex2.example6.exception;

public class InvalidRegHexagonException extends Exception {
    public InvalidRegHexagonException() {
        super();
    }

    public InvalidRegHexagonException(String message) {
        super(message);
    }

    public InvalidRegHexagonException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRegHexagonException(Throwable cause) {
        super(cause);
    }

    protected InvalidRegHexagonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
