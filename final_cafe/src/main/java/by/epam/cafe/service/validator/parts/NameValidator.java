package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.validator.Validator;

import java.util.regex.Pattern;

/**
 * Dedicated to validate {@link User#getName()}
 */
public class NameValidator implements Validator<String> {
    //language=RegExp
    public static final String NAME_REGEX = "^[\\p{javaAlphabetic}]+(([',. \\-][\\p{javaAlphabetic} ])?[\\p{javaAlphabetic}]*)*$";
    private static final Pattern COMPILE = Pattern.compile(NAME_REGEX, Pattern.UNICODE_CASE);

    @Override
    public boolean isValid(String input) {
        return COMPILE.matcher(input).matches();
    }
}
