package by.epam.task10.shop.service.exception;

public class NotEnoughSizeException extends Exception {
    public NotEnoughSizeException() {
    }

    public NotEnoughSizeException(String message) {
        super(message);
    }

    public NotEnoughSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughSizeException(Throwable cause) {
        super(cause);
    }
}
