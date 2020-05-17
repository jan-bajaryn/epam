package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

public class StreetValidator implements Validator<String> {
    @Override
    public boolean isValid(String input) {
        return true;
    }
}
