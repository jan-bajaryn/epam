package by.epam.task9.factory.exception;

public class IllegalCityParamsException extends Exception {
    public IllegalCityParamsException() {
    }

    public IllegalCityParamsException(String message) {
        super(message);
    }

    public IllegalCityParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCityParamsException(Throwable cause) {
        super(cause);
    }
}
