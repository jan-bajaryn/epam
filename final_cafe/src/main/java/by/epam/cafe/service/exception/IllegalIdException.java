package by.epam.cafe.service.exception;

public class IllegalIdException extends Exception {
    public IllegalIdException() {
    }

    public IllegalIdException(String message) {
        super(message);
    }

    public IllegalIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalIdException(Throwable cause) {
        super(cause);
    }
}
