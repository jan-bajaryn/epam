package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.NameValidator;

/**
 * Dedicated to parse String to name value
 */
public class NameParser extends ParamsParser<String> {
    private static NameParser INSTANCE = new NameParser();

    public static NameParser getInstance() {
        return INSTANCE;
    }


    private NameParser() {
        super(NameValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.isEmpty() ? null : input.strip();
    }
}
