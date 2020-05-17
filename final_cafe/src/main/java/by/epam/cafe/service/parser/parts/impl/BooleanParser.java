package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.BooleanParamValidator;

public class BooleanParser extends ParamsParser<Boolean> {


    private static final String TRUE = "1";

    private static final BooleanParamValidator validator = new BooleanParamValidator();

    public BooleanParser() {
        super(validator);
    }

    @Override
    protected Boolean modify(String input) throws Exception {
        return TRUE.equals(input);
    }
}
