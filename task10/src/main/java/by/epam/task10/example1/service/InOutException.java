package by.epam.task10.example1.service;

public class InOutException extends Exception {
    public InOutException() {
    }

    public InOutException(String message) {
        super(message);
    }

    public InOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public InOutException(Throwable cause) {
        super(cause);
    }
}
