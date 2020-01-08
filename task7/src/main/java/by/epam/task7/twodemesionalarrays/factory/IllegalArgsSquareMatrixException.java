package by.epam.task7.exercise4.ex40.factory;

import by.epam.task7.twodemesionalarrays.factory.IllegalArgsMatrixException;

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
