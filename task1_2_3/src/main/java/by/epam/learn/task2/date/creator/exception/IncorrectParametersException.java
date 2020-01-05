package by.epam.learn.task2.date.creator.exception;

public class IncorrectParametersException extends Exception {
    public IncorrectParametersException() {
    }

    public IncorrectParametersException(String message) {
        super(message);
    }

    public IncorrectParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectParametersException(Throwable cause) {
        super(cause);
    }
}
