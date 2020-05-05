package by.epam.cafe.service.parser.parts;

import by.epam.cafe.service.validator.parts.PriceValidator;

public class PriceParser extends ParamsParser<Integer> {

    private static final PriceValidator VALIDATOR = new PriceValidator();

    public PriceParser() {
        super(VALIDATOR);
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return Integer.valueOf(input);
    }
}
