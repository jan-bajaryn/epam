package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.Validator;

public class NoValidatorString implements Validator<String> {
    @Override
    public boolean isValid(String input) {
        return true;
    }
}
