package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.Validator;
import by.epam.cafe.service.validator.parts.PhoneValidator;

public class PhoneParser extends ParamsParser<String> {

    private static final PhoneValidator VALIDATOR = new PhoneValidator();

    public PhoneParser() {
        super(VALIDATOR);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.isEmpty() ? null : input.replaceAll("\\s", "");
    }
}
