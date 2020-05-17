package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.WeightValidator;

public class WeightParser extends ParamsParser<Integer> {

    private static final WeightValidator VALIDATOR = new WeightValidator();

    public WeightParser() {
        super(VALIDATOR);
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return Integer.valueOf(input);
    }
}
