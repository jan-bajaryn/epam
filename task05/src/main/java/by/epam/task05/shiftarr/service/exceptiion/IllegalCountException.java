package by.epam.task05.shiftarr.service.exceptiion;

public class IllegalCountException extends Exception {
    public IllegalCountException() {
        super();
    }

    public IllegalCountException(String message) {
        super(message);
    }

    public IllegalCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCountException(Throwable cause) {
        super(cause);
    }

}
