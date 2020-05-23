package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate Boolean value
 */
public class BooleanParamValidator implements Validator<Boolean> {
    private static BooleanParamValidator INSTANCE = new BooleanParamValidator();

    public static BooleanParamValidator getInstance() {
        return INSTANCE;
    }

    private BooleanParamValidator() {
    }

    @Override
    public boolean isValid(Boolean input) {
        return input != null;
    }
}
