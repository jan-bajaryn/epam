package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.Validator;

public class BooleanParamValidator implements Validator<Boolean> {
    @Override
    public boolean isValid(Boolean input) {
        return input != null;
    }
}
