package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.FloorValidator;

/**
 * Dedicated to parse String to floor value
 */
public class FloorParser extends ParamsParser<Integer> {

    private static final FloorValidator VALIDATOR = new FloorValidator();

    public FloorParser() {
        super(VALIDATOR);
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return input.isEmpty() ? null : Integer.valueOf(input);
    }
}
