package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.PhotoNameValidator;

public class PhotoNameParser extends ParamsParser<String> {

    private static final PhotoNameValidator VALIDATOR = new PhotoNameValidator();

    public PhotoNameParser() {
        super(VALIDATOR);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input;
    }
}
