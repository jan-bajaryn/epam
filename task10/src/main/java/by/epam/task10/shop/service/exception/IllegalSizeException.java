package by.epam.task10.shop.service.exception;

public class IllegalSizeException extends Exception {
    public IllegalSizeException() {
    }

    public IllegalSizeException(String message) {
        super(message);
    }

    public IllegalSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalSizeException(Throwable cause) {
        super(cause);
    }
}
