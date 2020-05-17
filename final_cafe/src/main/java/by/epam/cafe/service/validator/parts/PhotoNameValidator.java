package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

import java.util.regex.Pattern;

public class PhotoNameValidator implements Validator<String > {

    private static final String PHOTO_NAME_REGEX = "[-\\p{javaAlphabetic}\\d\\s().]{1,30}(\\.png|\\.jpg|\\.jpeg|\\.gif)";
    private static final Pattern COMPILE = Pattern.compile(PHOTO_NAME_REGEX, Pattern.UNICODE_CASE);


    @Override
    public boolean isValid(String input) {
        return input!=null && COMPILE.matcher(input).matches();
    }
}
