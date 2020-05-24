package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.StreetValidatorUser;

public class StreetParserUser extends ParamsParser<String> {
    private static StreetParserUser INSTANCE = new StreetParserUser();

    public static StreetParserUser getInstance() {
        return INSTANCE;
    }

    private StreetParserUser() {
        super(StreetValidatorUser.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
