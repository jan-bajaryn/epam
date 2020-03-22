package by.epam.cafe.service.exception;

public class NullServiceException extends Exception {
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
