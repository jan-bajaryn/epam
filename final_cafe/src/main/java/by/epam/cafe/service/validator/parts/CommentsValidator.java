package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.service.validator.Validator;
import java.util.regex.Pattern;

/**
 * Dedicated to validate {@link DeliveryInf#getComments()}
 */
public class CommentsValidator implements Validator<String> {
    private static CommentsValidator INSTANCE = new CommentsValidator();

    public static CommentsValidator getInstance() {
        return INSTANCE;
    }

    private CommentsValidator() {
    }
    private static final Pattern COMPILE = Pattern.compile("[\\s\\S]{0,200}");

    @Override
    public boolean isValid(String input) {
        return input == null || COMPILE.matcher(input).matches();
    }
}
