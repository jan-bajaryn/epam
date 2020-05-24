package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.ProductGroupInProductValidator;

/**
 * Dedicated to parse String to {@link Product#getProductGroup()} id value
 */
public class ProductGroupInProductParser extends ParamsParser<Integer> {
    private static ProductGroupInProductParser INSTANCE = new ProductGroupInProductParser();

    public static ProductGroupInProductParser getInstance() {
        return INSTANCE;
    }

    private ProductGroupInProductParser() {
        super(ProductGroupInProductValidator.getInstance());
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : Integer.valueOf(input);
    }
}
