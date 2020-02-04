package by.epam.task07.twodemesionalarrays.service.exception;

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
