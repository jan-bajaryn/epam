package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.PhoneValidator;

/**
 * Dedicated to parse String to phone value
 */
public class PhoneParser extends ParamsParser<String> {
    private static PhoneParser INSTANCE = new PhoneParser();

    public static PhoneParser getInstance() {
        return INSTANCE;
    }

    private PhoneParser() {
        super(PhoneValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.isEmpty() ? null : input.replaceAll("\\s", "");
    }
}
