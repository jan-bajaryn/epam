package by.epam.cafe.service.validator;

import by.epam.cafe.entity.impl.Product;

public class ProductValidator {

    private static final int MIN_PRICE = 0;
    private static final int MAX_PRICE = 100_000_000;
    private static final int MAX_WEIGHT = 100_000;
    private static final int MIN_WEIGHT = 0;

    public boolean isValid(Product product) {
        return validWithoutId(product) && product.getId() != null;
    }

    public boolean validWithoutId(Product product) {
        return product != null &&
                (product.getProductGroup() == null || product.getProductGroup().getId() != null) &&
                product.getPrice() != null && product.getPrice() >= MIN_PRICE && product.getPrice() < MAX_PRICE &&
                product.getWeight() != null && product.getWeight() > MIN_WEIGHT && product.getWeight() < MAX_WEIGHT;
    }
}