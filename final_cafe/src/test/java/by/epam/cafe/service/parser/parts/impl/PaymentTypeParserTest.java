package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PaymentTypeParserTest {

    private final PaymentTypeParser paymentTypeParser = new PaymentTypeParser();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false, null},
                {PaymentType.CASH.name(), true, PaymentType.CASH},
                {PaymentType.BANK_CARD.name(), true, PaymentType.BANK_CARD},
                {"Some value", false, null},
                {"waiting", false, null},
                {"delivering", false, null},
                {"что-то такое", false, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, PaymentType endValue) {
        if (result) {
            assertEquals(paymentTypeParser.parse(input).get(), endValue);
        } else {
            assertFalse(paymentTypeParser.parse(input).isPresent());
        }
    }
}