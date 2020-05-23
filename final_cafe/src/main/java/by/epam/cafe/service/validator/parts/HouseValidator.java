package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate house
 */
public class HouseValidator implements Validator<String> {
    private static HouseValidator INSTANCE = new HouseValidator();

    public static HouseValidator getInstance() {
        return INSTANCE;
    }

    private HouseValidator() {
    }

    @Override
    public boolean isValid(String input) {
        return input != null && !input.isEmpty();
    }
}
