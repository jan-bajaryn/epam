package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.SurnameValidator;

/**
 * Dedicated to parse String to surname value
 */
public class SurnameParser extends ParamsParser<String> {

    private static final SurnameValidator VALIDATOR = new SurnameValidator();

    public SurnameParser() {
        super(VALIDATOR);
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
