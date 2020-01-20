package by.epam.task10.shop.service.exception;

public class EmptySweetsException extends Exception {
    public EmptySweetsException() {
    }

    public EmptySweetsException(String message) {
        super(message);
    }

    public EmptySweetsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptySweetsException(Throwable cause) {
        super(cause);
    }
}
