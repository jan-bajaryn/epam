package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.HouseValidatorUser;

public class HouseParserUser extends ParamsParser<String> {

    private static HouseParserUser INSTANCE = new HouseParserUser();

    public static HouseParserUser getInstance() {
        return INSTANCE;
    }

    private HouseParserUser() {
        super(HouseValidatorUser.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
