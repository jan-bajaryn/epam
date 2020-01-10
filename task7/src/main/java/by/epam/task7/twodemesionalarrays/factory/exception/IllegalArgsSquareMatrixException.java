package by.epam.task7.twodemesionalarrays.factory.exception;

public class IllegalArgsSquareMatrixException extends IllegalArgsMatrixException {
    public IllegalArgsSquareMatrixException() {
    }

    public IllegalArgsSquareMatrixException(String message) {
        super(message);
    }

    public IllegalArgsSquareMatrixException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgsSquareMatrixException(Throwable cause) {
        super(cause);
    }
}
