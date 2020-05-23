package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate {@link ProductType}
 */
public class ProductTypeValidator implements Validator<ProductType> {
    private static ProductTypeValidator INSTANCE = new ProductTypeValidator();

    public static ProductTypeValidator getInstance() {
        return INSTANCE;
    }

    private ProductTypeValidator() {
    }
    @Override
    public boolean isValid(ProductType input) {
        return input != null;
    }
}
