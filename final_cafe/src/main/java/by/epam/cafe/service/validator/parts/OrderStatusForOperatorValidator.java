package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate {@link Order#getStatus()} value
 * for operator calling
 */
public class OrderStatusForOperatorValidator implements Validator<OrderStatus> {
    @Override
    public boolean isValid(OrderStatus input) {
        return input != null && input != OrderStatus.WAITING;
    }
}
