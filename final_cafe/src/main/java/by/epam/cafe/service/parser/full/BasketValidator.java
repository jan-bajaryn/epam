package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.impl.Product;

import java.util.Map;

import static by.epam.cafe.service.parser.helper.ValidateAndPutter.POSTFIX;

public class BasketValidator {

    private static final int MIN_VALUE = 1;

    public boolean isValid(Map<Product, Integer> basket, Map<String, String> redirect, String label) {
        if (basket == null || basket.isEmpty()) {
            wrongCallBack(redirect, label);
            return false;
        }
        boolean match = basket.entrySet().stream()
                .anyMatch(e -> {
                    Product key = e.getKey();
                    Integer value = e.getValue();
                    return key == null || key.getId() == null || value == null || value < MIN_VALUE;
                });
        if (match) {
            wrongCallBack(redirect, label);
            return false;
        }
        return true;
    }

    private void wrongCallBack(Map<String, String> redirect, String label) {
        redirect.put(label + POSTFIX, "true");
    }
}
