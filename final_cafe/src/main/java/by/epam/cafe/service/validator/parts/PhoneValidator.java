package by.epam.cafe.service.validator.parts;

public class PhoneValidator implements by.epam.cafe.service.validator.Validator<String> {

    public static final String PHONE_REGEX = "\\d{9}";

    @Override
    public boolean isValid(String input) {
        return input.matches(PHONE_REGEX);
    }
}
