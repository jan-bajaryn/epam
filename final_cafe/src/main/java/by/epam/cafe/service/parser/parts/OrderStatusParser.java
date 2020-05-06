package by.epam.cafe.service.parser.parts;

import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.service.validator.parts.OrderStatusValidator;

public class OrderStatusParser extends ParamsParser<OrderStatus> {
    private static final OrderStatusValidator VALIDATOR = new OrderStatusValidator();

    public OrderStatusParser() {
        super(VALIDATOR);
    }

    @Override
    protected OrderStatus modify(String input) throws Exception {
        return OrderStatus.valueOf(input);
    }
}
