package by.epam.demo_threads.example15;

public class ResourсeException extends Exception {
    public ResourсeException() {
        super();
    }
    public ResourсeException(String message, Throwable cause) {
        super(message, cause);
    }
    public ResourсeException(String message) {
        super(message);
    }
    public ResourсeException(Throwable cause) {
        super(cause);
    }
}
