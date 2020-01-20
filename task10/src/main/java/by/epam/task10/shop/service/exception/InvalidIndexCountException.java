package by.epam.task10.shop.service.exception;

public class InvalidIndexCountException extends Exception {
    public InvalidIndexCountException() {
    }

    public InvalidIndexCountException(String message) {
        super(message);
    }

    public InvalidIndexCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIndexCountException(Throwable cause) {
        super(cause);
    }
}
