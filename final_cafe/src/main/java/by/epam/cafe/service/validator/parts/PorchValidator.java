package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate porch
 */
public class PorchValidator implements Validator<Integer> {
    private static PorchValidator INSTANCE = new PorchValidator();

    public static PorchValidator getInstance() {
        return INSTANCE;
    }

    private PorchValidator() {
    }

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 50;

    @Override
    public boolean isValid(Integer input) {
        return input == null || (input > MIN_VALUE && input < MAX_VALUE);
    }
}
