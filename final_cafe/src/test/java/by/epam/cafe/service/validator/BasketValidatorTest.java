package by.epam.cafe.service.validator;

import by.epam.cafe.entity.db.impl.Product;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static by.epam.cafe.service.parser.helper.impl.ValidateAndPutterImpl.POSTFIX;
import static org.testng.Assert.*;

public class BasketValidatorTest {

    private final BasketValidator basketValidator = new BasketValidator();

    @Test
    public void testIsValid() {
        Map<Product, Integer> basket = new HashMap<>();
        Map<String, String> redirect = new HashMap<>();
        String label = "a";
        boolean valid = basketValidator.isValid(basket, redirect, label);
        assertFalse(valid);
    }

    @Test
    public void testIsValidNullInput() {
        Map<Product, Integer> basket = null;
        Map<String, String> redirect = new HashMap<>();
        String label = "a";
        boolean valid = basketValidator.isValid(basket, redirect, label);
        assertFalse(valid);
    }

    @Test
    public void testIsValidNullInputRedirect() {
        Map<Product, Integer> basket = new HashMap<>();
        Map<String, String> redirect = null;
        String label = "a";
        assertThrows(NullPointerException.class, () -> basketValidator.isValid(basket, redirect, label));
    }

    @Test
    public void testIsValidCheckCallback() {
        Map<Product, Integer> basket = new HashMap<>();
        Map<String, String> redirect = new HashMap<>();
        String label = "a";
        basketValidator.isValid(basket, redirect, label);
        assertTrue(redirect.containsKey("a" + POSTFIX));
    }

    @Test
    public void testIsValidPositive() {
        Map<Product, Integer> basket = new HashMap<>();
        basket.put(Product.newBuilder().id(1).build(), 1);
        Map<String, String> redirect = new HashMap<>();
        String label = "a";
        boolean valid = basketValidator.isValid(basket, redirect, label);
        assertTrue(valid);
    }

    @Test
    public void testIsValidNegative() {
        Map<Product, Integer> basket = new HashMap<>();
        basket.put(Product.newBuilder().id(1).build(), -1);
        Map<String, String> redirect = new HashMap<>();
        String label = "a";
        boolean valid = basketValidator.isValid(basket, redirect, label);
        assertFalse(valid);
    }
}