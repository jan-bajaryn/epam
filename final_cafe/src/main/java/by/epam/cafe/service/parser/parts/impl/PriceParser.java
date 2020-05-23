package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.PriceValidator;

/**
 * Dedicated to parse String to price value
 */
public class PriceParser extends ParamsParser<Integer> {
    private static PriceParser INSTANCE = new PriceParser();

    public static PriceParser getInstance() {
        return INSTANCE;
    }
    private static final PriceValidator VALIDATOR = PriceValidator.getInstance();

    private PriceParser() {
        super(VALIDATOR);
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return Integer.valueOf(input);
    }
}
