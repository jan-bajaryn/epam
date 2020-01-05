package by.epam.task4.ex1.exapmle1.creator.exception;

public class IllegalPointsTriangleException extends Exception {
    public IllegalPointsTriangleException() {
        super();
    }

    public IllegalPointsTriangleException(String message) {
        super(message);
    }

    public IllegalPointsTriangleException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPointsTriangleException(Throwable cause) {
        super(cause);
    }

    protected IllegalPointsTriangleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
