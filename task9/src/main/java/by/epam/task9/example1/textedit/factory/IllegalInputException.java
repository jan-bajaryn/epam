package by.epam.task9.example1.textedit.factory;

public class IllegalInputException extends Exception {
    public IllegalInputException() {
    }

    public IllegalInputException(String message) {
        super(message);
    }

    public IllegalInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputException(Throwable cause) {
        super(cause);
    }
}
