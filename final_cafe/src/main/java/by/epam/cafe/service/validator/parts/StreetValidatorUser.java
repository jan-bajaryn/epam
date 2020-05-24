package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

public class StreetValidatorUser implements Validator<String> {
    public static final int MAX_LENGTH = 50;

    private static StreetValidatorUser INSTANCE = new StreetValidatorUser();

    public static StreetValidatorUser getInstance() {
        return INSTANCE;
    }

    private StreetValidatorUser() {
    }

    @Override
    public boolean isValid(String input) {
        return input == null || (!input.isEmpty() && input.length() < MAX_LENGTH);
    }

}
