package by.epam.task10.shop.entity.factory;

public class IllegalParamsFactPackagingException extends Exception {
    public IllegalParamsFactPackagingException() {
    }

    public IllegalParamsFactPackagingException(String message) {
        super(message);
    }

    public IllegalParamsFactPackagingException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParamsFactPackagingException(Throwable cause) {
        super(cause);
    }
}
