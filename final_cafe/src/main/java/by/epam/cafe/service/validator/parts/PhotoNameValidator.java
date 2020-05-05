package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

public class PhotoNameValidator implements Validator<String > {

    private static final String PHOTO_NAME_REGEX = "[-\\w\\d\\s().]{1,30}(\\.png|\\.jpg|\\.jpeg|\\.gif)";


    @Override
    public boolean isValid(String input) {
        return input!=null && input.matches(PHOTO_NAME_REGEX);
    }
}
