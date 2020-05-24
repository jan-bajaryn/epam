package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.HouseValidatorOrder;

/**
 * Dedicated to parse String to house {@link DeliveryInf#getHouse()} value
 */
public class HouseParserOrder extends ParamsParser<String> {
    private static HouseParserOrder INSTANCE = new HouseParserOrder();

    public static HouseParserOrder getInstance() {
        return INSTANCE;
    }

    private HouseParserOrder() {
        super(HouseValidatorOrder.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
