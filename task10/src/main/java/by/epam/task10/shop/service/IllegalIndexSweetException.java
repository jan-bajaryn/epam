package by.epam.task10.shop.service;

public class IllegalIndexSweetException extends Exception {
    public IllegalIndexSweetException() {
    }

    public IllegalIndexSweetException(String message) {
        super(message);
    }

    public IllegalIndexSweetException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalIndexSweetException(Throwable cause) {
        super(cause);
    }
}
