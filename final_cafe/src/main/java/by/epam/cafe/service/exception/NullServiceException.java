package by.epam.cafe.service.exception;

/**
 * Indicates exceptions connected to {@code null}
 */
public class NullServiceException extends ServiceException {
    public NullServiceException() {
    }

    public NullServiceException(String message) {
        super(message);
    }

    public NullServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullServiceException(Throwable cause) {
        super(cause);
    }
}
