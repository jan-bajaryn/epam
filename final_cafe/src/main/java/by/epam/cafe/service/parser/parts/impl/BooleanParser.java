package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.BooleanParamValidator;

/**
 * Dedicated to parse String to Boolean value
 */
public class BooleanParser extends ParamsParser<Boolean> {
    private static BooleanParser INSTANCE = new BooleanParser();

    public static BooleanParser getInstance() {
        return INSTANCE;
    }

    private static final String TRUE = "1";

    private static final BooleanParamValidator validator = BooleanParamValidator.getInstance();

    private BooleanParser() {
        super(validator);
    }

    @Override
    protected Boolean modify(String input) throws Exception {
        return TRUE.equals(input);
    }
}
