package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

public class ProductGroupDescriptionValidator implements Validator<String> {
    private static final String DESCR_REGEX = "[\\w\\s\\d]{1,200}";

    @Override
    public boolean isValid(String input) {
        return input != null && input.matches(DESCR_REGEX);
    }
}
