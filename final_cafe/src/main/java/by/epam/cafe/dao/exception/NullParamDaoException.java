package by.epam.cafe.dao.exception;

public class NullParamDaoException extends Exception {
    public NullParamDaoException() {
    }

    public NullParamDaoException(String message) {
        super(message);
    }

    public NullParamDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullParamDaoException(Throwable cause) {
        super(cause);
    }
}
