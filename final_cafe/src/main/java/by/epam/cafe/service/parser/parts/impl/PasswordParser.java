package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.NoValidatorString;
import by.epam.cafe.service.validator.parts.PasswordValidator;

/**
 * Dedicated to parse String to password value
 */
public class PasswordParser extends ParamsParser<String> {

    public static final PasswordValidator VALIDATOR = new PasswordValidator();

    public PasswordParser() {
//        super(VALIDATOR);
        super(new NoValidatorString());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input;
    }
}
