package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.PaymentTypeValidator;

/**
 * Dedicated to parse String to {@link PaymentType}
 */
public class PaymentTypeParser extends ParamsParser<PaymentType> {

    private static final PaymentTypeValidator VALIDATOR = new PaymentTypeValidator();

    public PaymentTypeParser() {
        super(VALIDATOR);
    }

    @Override
    protected PaymentType modify(String input) throws Exception {
        return PaymentType.valueOf(input);
    }
}
