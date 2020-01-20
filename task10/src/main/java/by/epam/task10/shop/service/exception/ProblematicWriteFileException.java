package by.epam.task10.shop.service.exception;

public class ProblematicWriteFileException extends Exception {
    public ProblematicWriteFileException() {
    }

    public ProblematicWriteFileException(String message) {
        super(message);
    }

    public ProblematicWriteFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProblematicWriteFileException(Throwable cause) {
        super(cause);
    }
}
