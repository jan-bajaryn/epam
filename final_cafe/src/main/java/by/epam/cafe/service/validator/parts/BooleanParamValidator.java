package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate Boolean value
 */
public class BooleanParamValidator implements Validator<Boolean> {
    @Override
    public boolean isValid(Boolean input) {
        return input != null;
    }
}
