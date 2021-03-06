package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.WeightValidator;

/**
 * Dedicated to parse String to weight value
 */
public class WeightParser extends ParamsParser<Integer> {
    private static WeightParser INSTANCE = new WeightParser();

    public static WeightParser getInstance() {
        return INSTANCE;
    }


    private WeightParser() {
        super(WeightValidator.getInstance());
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return Integer.valueOf(input);
    }
}
