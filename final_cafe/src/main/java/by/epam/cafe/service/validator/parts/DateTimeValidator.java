package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class DateTimeValidator implements Validator<LocalDateTime> {
    private static final Logger log = LogManager.getLogger(DateTimeValidator.class);


    private static DateTimeValidator INSTANCE = new DateTimeValidator();

    public static DateTimeValidator getInstance() {
        return INSTANCE;
    }

    private DateTimeValidator() {
    }

    @Override
    public boolean isValid(LocalDateTime input) {
        log.debug("isValid: input = {}", input);
        return input != null;
    }
}
