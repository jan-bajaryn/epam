package by.epam.learn.task2.branching.example2.ex11.creator.exception;

public class WrongSidesTriangleException extends Exception {
    public WrongSidesTriangleException() {
    }

    public WrongSidesTriangleException(String message) {
        super(message);
    }

    public WrongSidesTriangleException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongSidesTriangleException(Throwable cause) {
        super(cause);
    }
}
