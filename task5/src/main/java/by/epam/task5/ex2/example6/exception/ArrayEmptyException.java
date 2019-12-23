package by.epam.task5.ex2.example6.exception;

public class ArrayEmptyException extends Exception {
    public ArrayEmptyException() {
        super();
    }

    public ArrayEmptyException(String message) {
        super(message);
    }

    public ArrayEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayEmptyException(Throwable cause) {
        super(cause);
    }

    protected ArrayEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
