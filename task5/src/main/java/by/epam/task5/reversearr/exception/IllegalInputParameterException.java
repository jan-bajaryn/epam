package by.epam.task5.reversearr.exception;

public class IllegalInputParameterException extends Exception {
    public IllegalInputParameterException() {
        super();
    }

    public IllegalInputParameterException(String message) {
        super(message);
    }

    public IllegalInputParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputParameterException(Throwable cause) {
        super(cause);
    }

    protected IllegalInputParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
