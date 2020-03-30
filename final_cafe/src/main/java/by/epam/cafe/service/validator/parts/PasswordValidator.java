package by.epam.cafe.service.validator.parts;

public class PasswordValidator {

    public static final String CAPITAL_REGEX = ".*\\p{javaUpperCase}";

    public static final String LOVER_REGEX = ".*\\p{javaLowerCase}";

    public static final String DIGIT_REGEX = ".*\\d.*";

    public boolean isValid(String password) {
        if (password == null || password.isEmpty() || password.length() < 8) {
            return false;
        }
        return password.matches(CAPITAL_REGEX)
                && password.matches(LOVER_REGEX)
                && password.matches(DIGIT_REGEX);
    }
}
