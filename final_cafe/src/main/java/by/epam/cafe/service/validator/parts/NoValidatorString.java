package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Validator what always return true
 */
public class NoValidatorString implements Validator<String> {
    private static NoValidatorString INSTANCE = new NoValidatorString();

    public static NoValidatorString getInstance() {
        return INSTANCE;
    }

    private NoValidatorString() {
    }
    @Override
    public boolean isValid(String input) {
        return true;
    }
}
