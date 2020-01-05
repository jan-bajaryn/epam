package by.epam.learn.task1.linear.example2.ex11.creator.exception;

public class WrongCathetException extends Exception {
    public WrongCathetException() {
    }

    public WrongCathetException(String message) {
        super(message);
    }

    public WrongCathetException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongCathetException(Throwable cause) {
        super(cause);
    }
}
