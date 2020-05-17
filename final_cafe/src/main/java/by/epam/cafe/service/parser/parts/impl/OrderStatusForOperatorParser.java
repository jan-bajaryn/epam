package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.OrderStatusForOperatorValidator;

public class OrderStatusForOperatorParser extends ParamsParser<OrderStatus> {
    private static final OrderStatusForOperatorValidator VALIDATOR = new OrderStatusForOperatorValidator();

    public OrderStatusForOperatorParser() {
        super(VALIDATOR);
    }

    @Override
    protected OrderStatus modify(String input) throws Exception {
        return OrderStatus.valueOf(input);
    }
}
