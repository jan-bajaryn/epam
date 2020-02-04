package by.epam.task05.ex2.example6.service.exception;

public class ArrayNullException extends Exception {
    public ArrayNullException() {
        super();
    }

    public ArrayNullException(String message) {
        super(message);
    }

    public ArrayNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayNullException(Throwable cause) {
        super(cause);
    }

}
