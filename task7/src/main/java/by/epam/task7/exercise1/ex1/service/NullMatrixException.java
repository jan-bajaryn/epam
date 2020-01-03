package by.epam.task7.exercise1.ex1.service;

public class NullMatrixException extends Exception {
    public NullMatrixException() {
    }

    public NullMatrixException(String message) {
        super(message);
    }

    public NullMatrixException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullMatrixException(Throwable cause) {
        super(cause);
    }
}
