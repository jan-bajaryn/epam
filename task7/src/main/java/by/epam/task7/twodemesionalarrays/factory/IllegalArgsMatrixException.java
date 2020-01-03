package by.epam.task7.twodemesionalarrays.factory;

public class IllegalArgsMatrixException extends Exception {
    public IllegalArgsMatrixException() {
        super();
    }

    public IllegalArgsMatrixException(String message) {
        super(message);
    }

    public IllegalArgsMatrixException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgsMatrixException(Throwable cause) {
        super(cause);
    }
}
