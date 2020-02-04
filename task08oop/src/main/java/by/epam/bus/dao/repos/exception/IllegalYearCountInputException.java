package by.epam.bus.dao.repos.exception;

public class IllegalYearCountInputException extends Exception {
    public IllegalYearCountInputException() {
    }

    public IllegalYearCountInputException(String message) {
        super(message);
    }

    public IllegalYearCountInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalYearCountInputException(Throwable cause) {
        super(cause);
    }

    public IllegalYearCountInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
