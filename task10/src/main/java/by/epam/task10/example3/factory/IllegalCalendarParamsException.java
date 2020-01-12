package by.epam.task10.example3.factory;

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
