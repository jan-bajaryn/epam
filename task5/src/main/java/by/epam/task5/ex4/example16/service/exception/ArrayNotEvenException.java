package by.epam.task5.ex4.example16.exception;

public class ArrayNotEvenException extends Exception {
    public ArrayNotEvenException() {
        super();
    }

    public ArrayNotEvenException(String message) {
        super(message);
    }

    public ArrayNotEvenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayNotEvenException(Throwable cause) {
        super(cause);
    }

    protected ArrayNotEvenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
