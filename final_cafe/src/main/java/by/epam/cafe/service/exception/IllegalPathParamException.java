package by.epam.cafe.service.exception;

public class IllegalPathParamException extends Exception {
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
