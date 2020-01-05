package by.epam.learn.task2.monthByName.exception;

public class IllegalInputNumberMonthException extends Exception {
    public IllegalInputNumberMonthException() {
    }

    public IllegalInputNumberMonthException(String message) {
        super(message);
    }

    public IllegalInputNumberMonthException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputNumberMonthException(Throwable cause) {
        super(cause);
    }
}
