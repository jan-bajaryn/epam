package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.PorchValidator;

/**
 * Dedicated to parse String to porch value
 */
public class PorchParser extends ParamsParser<Integer> {

    private static final PorchValidator VALIDATOR = new PorchValidator();

    public PorchParser() {
        super(VALIDATOR);
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return input == null || input.isEmpty() ? null : Integer.valueOf(input);
    }
}
