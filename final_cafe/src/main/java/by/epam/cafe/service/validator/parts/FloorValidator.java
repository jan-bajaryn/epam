package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.Validator;

public class FloorValidator implements Validator<Integer> {
    @Override
    public boolean isValid(Integer input) {
        return true;
    }
}
