package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

public class EmailValidator implements Validator<String> {

    private final org.apache.commons.validator.routines.EmailValidator emailValidator =
            org.apache.commons.validator.routines.EmailValidator.getInstance();

    @Override
    public boolean isValid(String input) {
        return emailValidator.isValid(input);
    }
}
