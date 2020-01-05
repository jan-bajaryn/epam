package by.epam.task5.ex1.example1.service.exception;

public class IllegalInputNaturalException extends Exception {
    public IllegalInputNaturalException() {
        super();
    }

    public IllegalInputNaturalException(String message) {
        super(message);
    }

    public IllegalInputNaturalException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputNaturalException(Throwable cause) {
        super(cause);
    }

    protected IllegalInputNaturalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
