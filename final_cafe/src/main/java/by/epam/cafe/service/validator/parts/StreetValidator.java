package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate street
 */
public class StreetValidator implements Validator<String> {
    private static StreetValidator INSTANCE = new StreetValidator();

    public static StreetValidator getInstance() {
        return INSTANCE;
    }

    private StreetValidator() {
    }
    @Override
    public boolean isValid(String input) {
        return true;
    }
}
