package by.epam.task10.shop.service.exception;

public class NoSweetFoundException extends Exception {
    public NoSweetFoundException() {
    }

    public NoSweetFoundException(String message) {
        super(message);
    }

    public NoSweetFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSweetFoundException(Throwable cause) {
        super(cause);
    }
}
