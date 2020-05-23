package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.OrderStatusForOperatorValidator;

/**
 * Dedicated to parse String to {@link Order#getStatus()} value
 * with operator calling
 */
public class OrderStatusForOperatorParser extends ParamsParser<OrderStatus> {

    private static OrderStatusForOperatorParser INSTANCE = new OrderStatusForOperatorParser();

    public static OrderStatusForOperatorParser getInstance() {
        return INSTANCE;
    }

    private static final OrderStatusForOperatorValidator VALIDATOR = OrderStatusForOperatorValidator.getInstance();

    private OrderStatusForOperatorParser() {
        super(VALIDATOR);
    }

    @Override
    protected OrderStatus modify(String input) throws Exception {
        return OrderStatus.valueOf(input);
    }
}
