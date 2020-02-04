package by.epam.task05.ex2.example6.service.exception;

public class ArrayEmptyException extends Exception {
    public ArrayEmptyException() {
        super();
    }

    public ArrayEmptyException(String message) {
        super(message);
    }

    public ArrayEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayEmptyException(Throwable cause) {
        super(cause);
    }

}
