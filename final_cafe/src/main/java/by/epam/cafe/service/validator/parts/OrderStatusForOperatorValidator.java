package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.service.validator.Validator;

public class OrderStatusForOperatorValidator implements Validator<OrderStatus> {
    @Override
    public boolean isValid(OrderStatus input) {
        return input != null && input!=OrderStatus.WAITING;
    }
}
