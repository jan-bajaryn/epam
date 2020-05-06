package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.parser.parts.impl.NameParser;
import by.epam.cafe.service.validator.Validator;

import java.util.regex.Pattern;

public class ProductGroupNameValidator implements Validator<String> {

    private static final String NAME_REGEX = "[\\p{javaAlphabetic}\\s\\d]{1,30}";
    private static final Pattern COMPILE = Pattern.compile(NAME_REGEX, Pattern.UNICODE_CASE);

    @Override
    public boolean isValid(String input) {
        return input != null && COMPILE.matcher(input).matches();
    }
}
