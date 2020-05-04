package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

public class UsernameValidator implements Validator<String> {

    private static final String USERNAME_REGEX = "\\w{1,20}";

    @Override
    public boolean isValid(String input) {
        return input.matches(USERNAME_REGEX);
    }
}
