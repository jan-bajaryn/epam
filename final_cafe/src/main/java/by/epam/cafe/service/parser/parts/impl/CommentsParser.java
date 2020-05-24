package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.CommentsValidator;

/**
 * Dedicated to parse String to {@link DeliveryInf#getComments()}
 */
public class CommentsParser extends ParamsParser<String> {
    private static CommentsParser INSTANCE = new CommentsParser();

    public static CommentsParser getInstance() {
        return INSTANCE;
    }

    private CommentsParser() {
        super(CommentsValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input == null || input.isEmpty() ? null : input.strip();
    }
}
