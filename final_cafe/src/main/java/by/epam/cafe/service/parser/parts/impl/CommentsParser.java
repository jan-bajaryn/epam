package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.CommentsValidator;

/**
 * Dedicated to parse String to {@link DeliveryInf#getComments()}
 */
public class CommentsParser extends ParamsParser<String> {

    private static final CommentsValidator validator = new CommentsValidator();

    public CommentsParser() {
        super(validator);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input == null || input.isEmpty() ? null : input.strip();
    }
}
