package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.HouseValidator;

/**
 * Dedicated to parse String to floor value
 */
public class HouseParser extends ParamsParser<String> {
    private static HouseParser INSTANCE = new HouseParser();

    public static HouseParser getInstance() {
        return INSTANCE;
    }

    private HouseParser() {
        super(HouseValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
