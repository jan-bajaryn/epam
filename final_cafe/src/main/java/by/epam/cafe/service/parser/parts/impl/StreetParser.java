package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.StreetValidator;

/**
 * Dedicated to parse String to street value
 */
public class StreetParser extends ParamsParser<String> {
    private static StreetParser INSTANCE = new StreetParser();

    public static StreetParser getInstance() {
        return INSTANCE;
    }
    private StreetParser() {
        super(StreetValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
