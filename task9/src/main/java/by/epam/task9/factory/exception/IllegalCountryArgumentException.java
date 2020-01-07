package by.epam.task9.factory.exception;

public class IllegalCountryArgumentException extends Exception {
    public IllegalCountryArgumentException() {
    }

    public IllegalCountryArgumentException(String message) {
        super(message);
    }

    public IllegalCountryArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCountryArgumentException(Throwable cause) {
        super(cause);
    }
}
