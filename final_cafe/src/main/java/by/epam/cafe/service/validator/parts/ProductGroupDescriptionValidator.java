package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

import java.util.regex.Pattern;

public class ProductGroupDescriptionValidator implements Validator<String> {
    private static final String DESCR_REGEX = "[\\p{javaAlphabetic}\\d\\s]{1,200}";
    private static final Pattern COMPILE = Pattern.compile(DESCR_REGEX, Pattern.UNICODE_CASE);

    @Override
    public boolean isValid(String input) {
        return input != null && COMPILE.matcher(input).matches();
    }
}
