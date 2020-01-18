package by.epam.task10.shop.entity.factory;

public class IllegalFactParamSweetException extends Exception {
    public IllegalFactParamSweetException() {
    }

    public IllegalFactParamSweetException(String message) {
        super(message);
    }

    public IllegalFactParamSweetException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFactParamSweetException(Throwable cause) {
        super(cause);
    }
}
