package by.epam.cafe.service.parser.parts;

import by.epam.cafe.service.validator.parts.PorchValidator;

public class PorchParser extends ParamsParser<Integer> {

    private static final PorchValidator VALIDATOR = new PorchValidator();

    public PorchParser() {
        super(VALIDATOR);
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return input.isEmpty() ? null : Integer.valueOf(input);
    }
}
