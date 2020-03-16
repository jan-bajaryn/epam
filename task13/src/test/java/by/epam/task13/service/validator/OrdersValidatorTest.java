package by.epam.task13.service.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrdersValidatorTest {

    @Test
    public void testIsValid() {
        OrdersValidator ov = new OrdersValidator();
        boolean valid = ov.isValid("D:\\Programming\\epam\\epam\\task13\\src\\main\\resources\\orders.xml");
        assertTrue(valid);
    }
}