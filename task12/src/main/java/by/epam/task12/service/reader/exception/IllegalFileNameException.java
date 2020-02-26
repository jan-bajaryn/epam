package by.epam.task12.service.reader.exception;

public class IllegalFileNameException extends Exception {
    public IllegalFileNameException() {
    }

    public IllegalFileNameException(String message) {
        super(message);
    }

    public IllegalFileNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFileNameException(Throwable cause) {
        super(cause);
    }
}
