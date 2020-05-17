package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PaymentTypeValidatorTest {

    private final PaymentTypeValidator paymentTypeValidator = new PaymentTypeValidator();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false},
                {PaymentType.CASH, true},
                {PaymentType.BANK_CARD, true},
        };
    }

    @Test(description = "Test for PaymentTypeValidator",
            dataProvider = "check")
    public void checkInput(PaymentType input, Boolean result) {
        assertEquals(paymentTypeValidator.isValid(input), (boolean) result);
    }
}