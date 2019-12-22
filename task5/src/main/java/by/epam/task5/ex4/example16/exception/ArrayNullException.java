package by.epam.task5.ex4.example16.exception;

public class ArrayNullException extends Exception {
    public ArrayNullException() {
        super();
    }

    public ArrayNullException(String message) {
        super(message);
    }

    public ArrayNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayNullException(Throwable cause) {
        super(cause);
    }

    protected ArrayNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
