package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.Validator;

import java.util.regex.Pattern;

public class CommentsValidator implements Validator<String> {

    private static final Pattern COMPILE = Pattern.compile("[\\s\\S]{0,200}");

    @Override
    public boolean isValid(String input) {
        return input == null || COMPILE.matcher(input).matches();
    }
}
