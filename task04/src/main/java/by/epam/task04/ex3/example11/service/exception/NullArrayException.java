package by.epam.task04.ex3.example11.service.exception;

public class NullArrayException extends Exception {
    public NullArrayException() {
        super();
    }

    public NullArrayException(String message) {
        super(message);
    }

    public NullArrayException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullArrayException(Throwable cause) {
        super(cause);
    }

    protected NullArrayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
