package by.epam.task12.entity.factory.exception;

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
