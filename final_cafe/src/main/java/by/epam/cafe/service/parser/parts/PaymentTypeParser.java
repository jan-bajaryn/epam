package by.epam.cafe.service.parser.parts;

import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.service.validator.Validator;
import by.epam.cafe.service.validator.parts.PaymentTypeValidator;

public class PaymentTypeParser extends ParamsParser<PaymentType>{

    private static final PaymentTypeValidator VALIDATOR = new PaymentTypeValidator();

    public PaymentTypeParser() {
        super(VALIDATOR);
    }

    @Override
    protected PaymentType modify(String input) throws Exception {
        return PaymentType.valueOf(input);
    }
}
