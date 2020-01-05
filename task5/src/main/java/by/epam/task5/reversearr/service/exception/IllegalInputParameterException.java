package by.epam.task5.reversearr.service.exception;

public class IllegalInputParameterException extends Exception {
    public IllegalInputParameterException() {
        super();
    }

    public IllegalInputParameterException(String message) {
        super(message);
    }

    public IllegalInputParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputParameterException(Throwable cause) {
        super(cause);
    }

}
