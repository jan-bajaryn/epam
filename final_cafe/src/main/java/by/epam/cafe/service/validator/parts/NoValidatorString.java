package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Validator what always return true
 */
public class NoValidatorString implements Validator<String> {
    @Override
    public boolean isValid(String input) {
        return true;
    }
}
