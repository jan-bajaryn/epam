package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

public class FloorValidator implements Validator<Integer> {

    private static final int MIN = -100;
    private static final int MAX = 100;

    @Override
    public boolean isValid(Integer input) {
        return input == null || (input < MAX && input > MIN);
    }
}
