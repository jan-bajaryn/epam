package by.epam.task10.textfile.entity.factory;

public class IllegalPathException extends Exception {
    public IllegalPathException() {
    }

    public IllegalPathException(String message) {
        super(message);
    }

    public IllegalPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPathException(Throwable cause) {
        super(cause);
    }
}
