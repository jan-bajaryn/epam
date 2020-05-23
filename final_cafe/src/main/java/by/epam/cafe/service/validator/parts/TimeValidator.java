package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.validator.Validator;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Dedicated to validate LocalDateTime for {@link DeliveryInf#getDeliveryTime()} value
 */
public class TimeValidator implements Validator<LocalDateTime> {
    private static TimeValidator INSTANCE = new TimeValidator();

    public static TimeValidator getInstance() {
        return INSTANCE;
    }

    private TimeValidator() {
    }

    public static final Duration MIN_DIFFERENCE = Duration.ofMinutes(30);
    private static final Duration MAX_DIFFERENCE = Duration.ofHours(24);

    @Override
    public boolean isValid(LocalDateTime input) {
        return input != null &&
                input.compareTo(LocalDateTime.now().plus(MIN_DIFFERENCE)) > 0 &&
                input.compareTo(LocalDateTime.now().plus(MAX_DIFFERENCE)) < 0;
    }
}
