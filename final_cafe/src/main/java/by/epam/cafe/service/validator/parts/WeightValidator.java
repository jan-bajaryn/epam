package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate weight
 */
public class WeightValidator implements Validator<Integer> {
    private static WeightValidator INSTANCE = new WeightValidator();

    public static WeightValidator getInstance() {
        return INSTANCE;
    }

    private WeightValidator() {
    }

    private static final int MAX = 1_000_000;
    private static final int MIN = 0;

    @Override
    public boolean isValid(Integer input) {
        return input > MIN && input <= MAX;
    }
}
