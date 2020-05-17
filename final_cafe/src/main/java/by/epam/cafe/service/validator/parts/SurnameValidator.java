package by.epam.cafe.service.validator.parts;


import by.epam.cafe.service.Validator;

import java.util.regex.Pattern;

public class SurnameValidator implements Validator<String> {
    //language=RegExp
    public static final String SURNAME_REGEX = "[\\p{javaAlphabetic}-]{1,20}";


    @Override
    public boolean isValid(String input) {
        return input == null || Pattern.compile(SURNAME_REGEX, Pattern.UNICODE_CASE).matcher(input).matches();
    }
}
