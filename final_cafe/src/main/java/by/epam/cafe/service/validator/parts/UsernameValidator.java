package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.db.impl.User;
import by.epam.cafe.service.validator.Validator;

import java.util.regex.Pattern;

/**
 * Dedicated to validate {@link User#getUsername()}
 */
public class UsernameValidator implements Validator<String> {

    private static final String USERNAME_REGEX = "[\\p{javaAlphabetic}\\d]{1,20}";

    @Override
    public boolean isValid(String input) {
        return Pattern.compile(USERNAME_REGEX, Pattern.UNICODE_CASE).matcher(input).matches();
    }
}
