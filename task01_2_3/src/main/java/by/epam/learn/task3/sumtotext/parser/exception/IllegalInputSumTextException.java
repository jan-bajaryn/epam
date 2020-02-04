package by.epam.learn.task3.sumtotext.parser.exception;

public class IllegalInputSumTextException extends Exception {
    public IllegalInputSumTextException() {
    }

    public IllegalInputSumTextException(String message) {
        super(message);
    }

    public IllegalInputSumTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputSumTextException(Throwable cause) {
        super(cause);
    }
}
