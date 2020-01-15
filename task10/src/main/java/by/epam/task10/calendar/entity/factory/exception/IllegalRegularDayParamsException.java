package by.epam.task10.calendar.entity.factory.exception;

public class IllegalRegularDayParamsException extends Exception {
    public IllegalRegularDayParamsException() {
    }

    public IllegalRegularDayParamsException(String message) {
        super(message);
    }

    public IllegalRegularDayParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRegularDayParamsException(Throwable cause) {
        super(cause);
    }
}
