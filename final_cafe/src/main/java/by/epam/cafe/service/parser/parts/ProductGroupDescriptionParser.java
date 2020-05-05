package by.epam.cafe.service.parser.parts;

import by.epam.cafe.service.validator.Validator;
import by.epam.cafe.service.validator.parts.ProductGroupDescriptionValidator;

public class ProductGroupDescriptionParser extends ParamsParser<String> {

    private static final ProductGroupDescriptionValidator validator = new ProductGroupDescriptionValidator();

    public ProductGroupDescriptionParser() {
        super(validator);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.strip();
    }
}
