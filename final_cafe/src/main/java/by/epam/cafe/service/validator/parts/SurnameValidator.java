package by.epam.cafe.service.validator.parts;


public class SurnameValidator implements by.epam.cafe.service.validator.Validator<String> {
    //language=RegExp
    public static final String SURNAME_REGEX = "\\w{1,20}";


    @Override
    public boolean isValid(String input) {
        return input == null || input.matches(SURNAME_REGEX);
    }
}
