package by.epam.task10.shop.service.exception;

public class NoElementsToExchangeException extends Exception {
    public NoElementsToExchangeException() {
    }

    public NoElementsToExchangeException(String message) {
        super(message);
    }

    public NoElementsToExchangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoElementsToExchangeException(Throwable cause) {
        super(cause);
    }
}
