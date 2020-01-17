package by.epam.task10.calendar.entity.factory.exception;

public class IllegalParamsSpecDateException extends Exception {
    public IllegalParamsSpecDateException() {
    }

    public IllegalParamsSpecDateException(String message) {
        super(message);
    }

    public IllegalParamsSpecDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParamsSpecDateException(Throwable cause) {
        super(cause);
    }
}
