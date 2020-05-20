package by.epam.cafe.service.exception;

import by.epam.cafe.entity.db.Entity;

/**
 * Indicates when parameter is invalid id
 */
public class IllegalIdException extends ServiceException {
    public IllegalIdException() {
    }

    public IllegalIdException(String message) {
        super(message);
    }

    public IllegalIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalIdException(Throwable cause) {
        super(cause);
    }
}
