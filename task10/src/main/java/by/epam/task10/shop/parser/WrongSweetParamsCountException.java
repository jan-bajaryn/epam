package by.epam.task10.shop.parser;

public class WrongSweetParamsCountException extends Exception {
    public WrongSweetParamsCountException() {
    }

    public WrongSweetParamsCountException(String message) {
        super(message);
    }

    public WrongSweetParamsCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongSweetParamsCountException(Throwable cause) {
        super(cause);
    }
}
