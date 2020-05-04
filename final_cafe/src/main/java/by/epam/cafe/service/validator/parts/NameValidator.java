package by.epam.cafe.service.validator.parts;

public class NameValidator implements by.epam.cafe.service.validator.Validator<String> {
    //language=RegExp
    public static final String NAME_REGEX = "^[a-zA-Z]+(([',. \\-][a-zA-Z ])?[a-zA-Z]*)*$";

    @Override
    public boolean isValid(String input) {
        return input.matches(NAME_REGEX);
    }
}
