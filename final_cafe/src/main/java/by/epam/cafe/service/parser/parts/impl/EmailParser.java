package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.EmailValidator;

/**
 * Dedicated to parse String to {@link DeliveryInf#getEmail()}
 */
public class EmailParser extends ParamsParser<String> {
    private static EmailParser INSTANCE = new EmailParser();

    public static EmailParser getInstance() {
        return INSTANCE;
    }
    private static final EmailValidator VALIDATOR = EmailValidator.getInstance();

    private EmailParser() {
        super(VALIDATOR);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.isEmpty() ? null : input.strip();
    }
}
