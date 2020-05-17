package by.epam.cafe.service.parser.parts;

import by.epam.cafe.service.parser.ParamsParser;
import by.epam.cafe.service.validator.parts.NameValidator;

public class NameParser extends ParamsParser<String> {

    private static final NameValidator VALIDATOR = new NameValidator();

    public NameParser() {
        super(VALIDATOR);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.isEmpty() ? null : input.strip();
    }
}
