package by.epam.task7.twodemesionalarrays.view;

import java.util.InputMismatchException;

public class IllegalInputReaderException extends Exception {
    public IllegalInputReaderException() {
    }

    public IllegalInputReaderException(String message) {
        super(message);
    }

    public IllegalInputReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputReaderException(Throwable cause) {
        super(cause);
    }
}
