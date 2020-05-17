package by.epam.cafe.service.parser.parts;

import by.epam.cafe.service.parser.ParamsParser;
import by.epam.cafe.service.validator.parts.ProductGroupNameValidator;

public class ProductGroupNameParser extends ParamsParser<String> {

    private static final ProductGroupNameValidator VALIDATOR = new ProductGroupNameValidator();

    public ProductGroupNameParser() {
        super(VALIDATOR);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.strip();
    }
}
