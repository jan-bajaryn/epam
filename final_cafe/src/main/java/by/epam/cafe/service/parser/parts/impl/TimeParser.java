package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.TimeValidator;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Dedicated to parse String to LocalDateTime for {@link DeliveryInf#getDeliveryTime()} value
 */
public class TimeParser extends ParamsParser<LocalDateTime> {
    private static TimeParser INSTANCE = new TimeParser();

    public static TimeParser getInstance() {
        return INSTANCE;
    }

    private static final String FORMAT = "HH:mm";

    private static final TimeValidator VALIDATOR = TimeValidator.getInstance();

    private TimeParser() {
        super(VALIDATOR);
    }

    @Override
    protected LocalDateTime modify(String input) throws Exception {
        LocalTime localTime = LocalTime.parse(input, DateTimeFormatter.ofPattern(FORMAT));
        LocalDateTime time = LocalDateTime.now()
                .withHour(localTime.getHour())
                .withMinute(localTime.getMinute())
                .withSecond(0)
                .withNano(0);

        if (time.compareTo(LocalDateTime.now()) < 0) {
            time = time.plusDays(1);
        }
        return time;
    }
}
