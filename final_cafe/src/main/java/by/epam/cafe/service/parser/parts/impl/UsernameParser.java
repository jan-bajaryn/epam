package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.UsernameValidator;

/**
 * Dedicated to parse String to {@link User#getUsername()} value
 */
public class UsernameParser extends ParamsParser<String> {

    private static final UsernameValidator VALIDATOR = new UsernameValidator();

    public UsernameParser() {
        super(VALIDATOR);
    }


    @Override
    protected String modify(String input) {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
