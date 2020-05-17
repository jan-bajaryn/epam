package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.impl.Order;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderStatusForOperatorValidatorTest {

    private final OrderStatusForOperatorValidator orderStatusForOperatorValidator =
            new OrderStatusForOperatorValidator();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false},
                {OrderStatus.WAITING, false},
                {OrderStatus.CONFIRMED, true},
                {OrderStatus.CANCELED, true},
                {OrderStatus.DELIVERED, true},
                {OrderStatus.DELIVERING, true},
        };
    }

    @Test(description = "Test for OrderStatusForOperatorValidator",
            dataProvider = "check")
    public void checkInput(OrderStatus input, Boolean result) {
        assertEquals(orderStatusForOperatorValidator.isValid(input), (boolean) result);
    }
}