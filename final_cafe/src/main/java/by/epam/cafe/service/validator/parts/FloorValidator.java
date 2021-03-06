package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate floor
 */
public class FloorValidator implements Validator<Integer> {
    private static FloorValidator INSTANCE = new FloorValidator();

    public static FloorValidator getInstance() {
        return INSTANCE;
    }

    private FloorValidator() {
    }

    private static final int MIN = -100;
    private static final int MAX = 100;

    @Override
    public boolean isValid(Integer input) {
        return input == null || (input < MAX && input > MIN);
    }
}
