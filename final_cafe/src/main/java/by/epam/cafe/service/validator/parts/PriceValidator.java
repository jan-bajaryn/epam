package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

public class PriceValidator implements Validator<Integer> {

    private static final int MAX_VALUE = 10000;
    private static final int MIN_VALUE = 0;

    @Override
    public boolean isValid(Integer input) {
        return input >= MIN_VALUE && input < MAX_VALUE;
    }
}
