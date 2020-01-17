package by.epam.task10.calendar.service.validator;

import by.epam.task10.calendar.entity.impl.RegularFreeCelebrity;

import java.util.regex.Pattern;

public class RegularDayValidator {
    public static final Pattern REG_DAY_EXPR = Pattern.compile("(\\w+\\s*)*");

    public boolean isValid(RegularFreeCelebrity regularDay) {
        return regularDay.getDirection() != null &&
                !regularDay.getDescription().contains("\n") &&
                REG_DAY_EXPR.matcher(regularDay.getName()).matches();
    }
}
