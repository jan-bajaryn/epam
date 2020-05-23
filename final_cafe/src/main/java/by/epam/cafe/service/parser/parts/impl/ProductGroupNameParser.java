package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.ProductGroupNameValidator;

/**
 * Dedicated to parse String to {@link ProductGroup#getName()} value
 */
public class ProductGroupNameParser extends ParamsParser<String> {
    private static ProductGroupNameParser INSTANCE = new ProductGroupNameParser();

    public static ProductGroupNameParser getInstance() {
        return INSTANCE;
    }
    private static final ProductGroupNameValidator VALIDATOR = ProductGroupNameValidator.getInstance();

    private ProductGroupNameParser() {
        super(VALIDATOR);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.strip();
    }
}
