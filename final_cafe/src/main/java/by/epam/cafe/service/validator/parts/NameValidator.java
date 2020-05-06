package by.epam.cafe.service.validator.parts;

import java.util.regex.Pattern;

public class NameValidator implements by.epam.cafe.service.validator.Validator<String> {
    //language=RegExp
    public static final String NAME_REGEX = "^[\\p{javaAlphabetic}]+(([',. \\-][\\p{javaAlphabetic} ])?[\\p{javaAlphabetic}]*)*$";
    private static final Pattern COMPILE = Pattern.compile(NAME_REGEX, Pattern.UNICODE_CASE);

    @Override
    public boolean isValid(String input) {
        return COMPILE.matcher(input).matches();
    }
}
