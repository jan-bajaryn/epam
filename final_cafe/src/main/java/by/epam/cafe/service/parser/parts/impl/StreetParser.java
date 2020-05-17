package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.StreetValidator;

public class StreetParser extends ParamsParser<String> {
    public StreetParser() {
        super(new StreetValidator());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
