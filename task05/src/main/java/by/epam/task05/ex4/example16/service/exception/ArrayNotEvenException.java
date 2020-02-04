package by.epam.task05.ex4.example16.service.exception;

public class ArrayNotEvenException extends Exception {
    public ArrayNotEvenException() {
        super();
    }

    public ArrayNotEvenException(String message) {
        super(message);
    }

    public ArrayNotEvenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayNotEvenException(Throwable cause) {
        super(cause);
    }

}
