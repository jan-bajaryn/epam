package by.epam.task05.ex3.example11.service.exception;

public class IllegalInputArgumentException extends Exception {
    public IllegalInputArgumentException() {
        super();
    }

    public IllegalInputArgumentException(String message) {
        super(message);
    }

    public IllegalInputArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputArgumentException(Throwable cause) {
        super(cause);
    }

}
