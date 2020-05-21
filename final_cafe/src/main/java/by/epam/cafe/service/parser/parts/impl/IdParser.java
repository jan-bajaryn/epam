package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.IdValidator;

/**
 * Dedicated to parse String to id of Integer
 */
public class IdParser extends ParamsParser<Integer> {

    public static final IdValidator VALIDATOR = new IdValidator();

    public IdParser() {
        super(VALIDATOR);
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return Integer.valueOf(input);
    }
}
