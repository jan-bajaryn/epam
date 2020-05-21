package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate id with Integer type
 */
public class IdValidator implements Validator<Integer> {

    private static final int MIN_EXCLUDE_VALUE = 0;

    @Override
    public boolean isValid(Integer input) {
        return input != null && input > MIN_EXCLUDE_VALUE;
    }
}
