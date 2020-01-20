package by.epam.task10.shop.service.exception;

public class NullPackagingException extends Exception {
    public NullPackagingException() {
    }

    public NullPackagingException(String message) {
        super(message);
    }

    public NullPackagingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullPackagingException(Throwable cause) {
        super(cause);
    }
}
