package by.epam.cafe.service.exception;

/**
 * Indicates that path variable is invalid
 */
public class IllegalPathParamException extends ServiceException {
    public IllegalPathParamException() {
    }

    public IllegalPathParamException(String message) {
        super(message);
    }

    public IllegalPathParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPathParamException(Throwable cause) {
        super(cause);
    }
}
