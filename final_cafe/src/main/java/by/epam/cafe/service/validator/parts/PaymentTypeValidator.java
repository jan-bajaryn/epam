package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate {@link PaymentType}
 */
public class PaymentTypeValidator implements Validator<PaymentType> {
    private static PaymentTypeValidator INSTANCE = new PaymentTypeValidator();

    public static PaymentTypeValidator getInstance() {
        return INSTANCE;
    }

    private PaymentTypeValidator() {
    }
    @Override
    public boolean isValid(PaymentType input) {
        return input != null;
    }
}
