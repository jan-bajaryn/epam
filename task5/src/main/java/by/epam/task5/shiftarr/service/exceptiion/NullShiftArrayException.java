package by.epam.task5.shiftarr.service.exceptiion;

public class NullShiftArrayException extends Exception {
    public NullShiftArrayException() {
        super();
    }

    public NullShiftArrayException(String message) {
        super(message);
    }

    public NullShiftArrayException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullShiftArrayException(Throwable cause) {
        super(cause);
    }

}
