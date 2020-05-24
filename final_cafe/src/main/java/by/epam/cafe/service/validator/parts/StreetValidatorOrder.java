package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate street
 */
public class StreetValidatorOrder implements Validator<String> {
    public static final int MAX_LENGTH = 50;
    private static StreetValidatorOrder INSTANCE = new StreetValidatorOrder();

    public static StreetValidatorOrder getInstance() {
        return INSTANCE;
    }

    private StreetValidatorOrder() {
    }

    @Override
    public boolean isValid(String input) {
        return input != null && !input.isEmpty() && input.length() < MAX_LENGTH;
    }

}
