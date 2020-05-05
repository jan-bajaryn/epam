package by.epam.cafe.service.parser.parts;

import by.epam.cafe.service.validator.parts.BooleanParamValidator;

public class DisabledParser extends ParamsParser<Boolean> {


    private static final String TRUE = "1";

    private static final BooleanParamValidator validator = new BooleanParamValidator();

    public DisabledParser() {
        super(validator);
    }

    @Override
    protected Boolean modify(String input) throws Exception {
        return TRUE.equals(input);
    }
}
