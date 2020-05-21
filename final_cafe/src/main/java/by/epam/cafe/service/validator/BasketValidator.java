package by.epam.cafe.service.validator;

import by.epam.cafe.entity.db.impl.Product;

import java.util.Map;

import static by.epam.cafe.service.parser.helper.impl.ValidateAndPutterImpl.POSTFIX;

/**
 * Dedicated to validate basket
 */
public class BasketValidator {

    private static final int MIN_VALUE = 1;

    /**
     * @param basket   vocabulary with product and count of product identified by id {@link Product#getId()}
     * @param redirect Map to return what parameter is valid, and value with
     *                 what parameter was in input
     *                 First String in the map is the name of parameter
     *                 Second String in the map is value of input in parameter
     *                 or information about existing error in the map
     *                 For example {street, abcde} means that input for
     *                 parameter of name "street" was "abcde"
     *                 {street_error, true} means that in parameter
     *                 of name "street" was error.
     * @param label    String value to identify message what should be put in response
     * @return true if basket is not null, not empty, if count of product isn't less than
     * {@link BasketValidator#MIN_VALUE} and each product not null
     */
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
