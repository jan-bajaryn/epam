package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.StreetValidatorOrder;

/**
 * Dedicated to parse String to {@link DeliveryInf#getStreet()} value
 */
public class StreetParserOrder extends ParamsParser<String> {
    private static StreetParserOrder INSTANCE = new StreetParserOrder();

    public static StreetParserOrder getInstance() {
        return INSTANCE;
    }

    private StreetParserOrder() {
        super(StreetValidatorOrder.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
