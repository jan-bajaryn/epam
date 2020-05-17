package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.service.validator.Validator;

public class PaymentTypeValidator implements Validator<PaymentType> {
    @Override
    public boolean isValid(PaymentType input) {
        return input != null;
    }
}
