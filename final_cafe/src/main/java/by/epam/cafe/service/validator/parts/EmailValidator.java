package by.epam.cafe.service.validator.parts;

public class EmailValidator implements by.epam.cafe.service.validator.Validator<String> {

    private final org.apache.commons.validator.routines.EmailValidator emailValidator =
            org.apache.commons.validator.routines.EmailValidator.getInstance();

    @Override
    public boolean isValid(String input) {
        return emailValidator.isValid(input);
    }
}
