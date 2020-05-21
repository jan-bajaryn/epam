package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.EmailValidator;

/**
 * Dedicated to parse String to {@link DeliveryInf#getEmail()}
 */
public class EmailParser extends ParamsParser<String> {

    private static final EmailValidator VALIDATOR = new EmailValidator();

    public EmailParser() {
        super(VALIDATOR);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.isEmpty() ? null : input.strip();
    }
}
