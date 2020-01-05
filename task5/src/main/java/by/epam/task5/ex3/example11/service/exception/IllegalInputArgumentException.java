package by.epam.task5.ex3.example11.exception;

public class IllegalInputArgumentException extends Exception {
    public IllegalInputArgumentException() {
        super();
    }

    public IllegalInputArgumentException(String message) {
        super(message);
    }

    public IllegalInputArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputArgumentException(Throwable cause) {
        super(cause);
    }

    protected IllegalInputArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
