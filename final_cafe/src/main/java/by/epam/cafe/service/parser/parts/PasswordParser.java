package by.epam.cafe.service.parser.parts;

import by.epam.cafe.service.parser.ParamsParser;
import by.epam.cafe.service.validator.parts.NoValidatorString;
import by.epam.cafe.service.validator.parts.PasswordValidator;

public class PasswordParser extends ParamsParser<String> {

    // TODO enable validation before answer
    public static final PasswordValidator VALIDATOR = new PasswordValidator();

    public PasswordParser() {
//        super(VALIDATOR);
        super(new NoValidatorString());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input;
    }
}
