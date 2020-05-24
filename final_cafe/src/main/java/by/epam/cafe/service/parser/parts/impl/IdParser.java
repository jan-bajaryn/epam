package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.IdValidator;

/**
 * Dedicated to parse String to id of Integer
 */
public class IdParser extends ParamsParser<Integer> {
    private static IdParser INSTANCE = new IdParser();

    public static IdParser getInstance() {
        return INSTANCE;
    }

    private IdParser() {
        super(IdValidator.getInstance());
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return Integer.valueOf(input);
    }
}
