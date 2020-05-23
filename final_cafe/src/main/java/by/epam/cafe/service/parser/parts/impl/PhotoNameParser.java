package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.db.impl.ProductGroup;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.PhotoNameValidator;

/**
 * Dedicated to parse String to {@link ProductGroup#getPhotoName()} value
 */
public class PhotoNameParser extends ParamsParser<String> {
    private static PhotoNameParser INSTANCE = new PhotoNameParser();

    public static PhotoNameParser getInstance() {
        return INSTANCE;
    }
    private static final PhotoNameValidator VALIDATOR = PhotoNameValidator.getInstance();

    private PhotoNameParser() {
        super(VALIDATOR);
    }

    @Override
    protected String modify(String input) throws Exception {
        return input;
    }
}
