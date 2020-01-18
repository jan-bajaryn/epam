package by.epam.task10.shop.parser;

public class IllegalParamsPackingException extends Exception {
    public IllegalParamsPackingException() {
    }

    public IllegalParamsPackingException(String message) {
        super(message);
    }

    public IllegalParamsPackingException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParamsPackingException(Throwable cause) {
        super(cause);
    }
}
