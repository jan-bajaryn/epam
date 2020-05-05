package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

public class ProductGroupNameValidator implements Validator<String> {

    private static final String NAME_REGEX = "[\\w\\s\\d]{1,30}";

    @Override
    public boolean isValid(String input) {
        return input != null && input.matches(NAME_REGEX);
    }
}
