package by.epam.task10.calendar.entity.factory.exception;

public class IllegalCalendarParamsException extends Exception {
    public IllegalCalendarParamsException() {
    }

    public IllegalCalendarParamsException(String message) {
        super(message);
    }

    public IllegalCalendarParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCalendarParamsException(Throwable cause) {
        super(cause);
    }
}
