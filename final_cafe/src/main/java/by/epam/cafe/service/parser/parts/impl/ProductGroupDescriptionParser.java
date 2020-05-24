package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.ProductGroupDescriptionValidator;

/**
 * Dedicated to parse String to {@link ProductGroup#getDescription()} value
 */
public class ProductGroupDescriptionParser extends ParamsParser<String> {
    private static ProductGroupDescriptionParser INSTANCE = new ProductGroupDescriptionParser();

    public static ProductGroupDescriptionParser getInstance() {
        return INSTANCE;
    }

    private ProductGroupDescriptionParser() {
        super(ProductGroupDescriptionValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.strip();
    }
}
