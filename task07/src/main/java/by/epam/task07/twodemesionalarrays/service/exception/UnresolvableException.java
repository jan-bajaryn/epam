package by.epam.task07.twodemesionalarrays.service.exception;

public class UnresolvableException extends Exception {
    public UnresolvableException() {
    }

    public UnresolvableException(String message) {
        super(message);
    }

    public UnresolvableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnresolvableException(Throwable cause) {
        super(cause);
    }
}
