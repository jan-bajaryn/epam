package by.epam.cafe.service.parser.parts;

import by.epam.cafe.service.parser.ParamsParser;
import by.epam.cafe.service.validator.parts.EmailValidator;

public class EmailParser extends ParamsParser<String> {

    private static final EmailValidator VALIDATOR = new EmailValidator();

    public EmailParser() {
        super(VALIDATOR);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.isEmpty() ? null : input.strip();
    }
}
