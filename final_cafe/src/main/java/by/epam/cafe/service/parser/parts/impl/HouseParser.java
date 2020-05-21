package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.HouseValidator;

/**
 * Dedicated to parse String to floor value
 */
public class HouseParser extends ParamsParser<String> {
    public HouseParser() {
        super(new HouseValidator());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
