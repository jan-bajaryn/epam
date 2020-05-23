package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.RoomValidator;

/**
 * Dedicated to parse String to room value
 */
public class RoomParser extends ParamsParser<String> {
    private static final RoomValidator VALIDATOR = RoomValidator.getInstance();
    private static RoomParser INSTANCE = new RoomParser();

    public static RoomParser getInstance() {
        return INSTANCE;
    }

    private RoomParser() {
        super(VALIDATOR);
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
