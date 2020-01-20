package by.epam.task10.shop.service.exception;

public class NotContainsPackagingException extends Exception {
    public NotContainsPackagingException() {
    }

    public NotContainsPackagingException(String message) {
        super(message);
    }

    public NotContainsPackagingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotContainsPackagingException(Throwable cause) {
        super(cause);
    }
}
