package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.RoomValidator;

public class RoomParser extends ParamsParser<String> {
    public RoomParser() {
        super(new RoomValidator());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
