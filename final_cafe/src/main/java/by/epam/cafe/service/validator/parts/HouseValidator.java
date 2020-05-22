package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate house
 */
public class HouseValidator implements Validator<String> {
    @Override
    public boolean isValid(String input) {
        return input != null && !input.isEmpty();
    }
}
