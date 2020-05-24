package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.enums.ProductType;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.ProductTypeValidator;

/**
 * Dedicated to parse String to {@link ProductType} value
 */
public class ProductTypeParser extends ParamsParser<ProductType> {
    private static ProductTypeParser INSTANCE = new ProductTypeParser();

    public static ProductTypeParser getInstance() {
        return INSTANCE;
    }

    private ProductTypeParser() {
        super(ProductTypeValidator.getInstance());
    }

    @Override
    protected ProductType modify(String input) throws Exception {
        return ProductType.valueOf(input);
    }
}
