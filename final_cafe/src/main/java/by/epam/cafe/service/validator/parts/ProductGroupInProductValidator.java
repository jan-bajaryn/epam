package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate {@link Product#getProductGroup()} id
 */
public class ProductGroupInProductValidator implements Validator<Integer> {

    private static final int MIN_VALUE = 0;

    @Override
    public boolean isValid(Integer input) {
        return input == null || input > MIN_VALUE;
    }
}
