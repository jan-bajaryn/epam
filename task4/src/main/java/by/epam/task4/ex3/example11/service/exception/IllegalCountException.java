package by.epam.task4.ex3.example11.service.exception;

public class IllegalCountException extends Exception {
    public IllegalCountException() {
        super();
    }

    public IllegalCountException(String message) {
        super(message);
    }

    public IllegalCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCountException(Throwable cause) {
        super(cause);
    }

    protected IllegalCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
