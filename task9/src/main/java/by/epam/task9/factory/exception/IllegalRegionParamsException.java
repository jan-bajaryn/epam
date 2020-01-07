package by.epam.task9.factory.exception;

public class IllegalRegionParamsException extends Exception {
    public IllegalRegionParamsException() {
    }

    public IllegalRegionParamsException(String message) {
        super(message);
    }

    public IllegalRegionParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRegionParamsException(Throwable cause) {
        super(cause);
    }
}
