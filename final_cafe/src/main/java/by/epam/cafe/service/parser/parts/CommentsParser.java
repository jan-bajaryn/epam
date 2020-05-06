package by.epam.cafe.service.parser.parts;

import by.epam.cafe.service.validator.Validator;
import by.epam.cafe.service.validator.parts.CommentsValidator;

public class CommentsParser extends ParamsParser<String> {

    private static final CommentsValidator validator = new CommentsValidator();

    public CommentsParser() {
        super(validator);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.isEmpty() ? null : input.strip();
    }
}
