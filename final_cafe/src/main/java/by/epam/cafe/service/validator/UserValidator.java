package by.epam.cafe.service.validator;

import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.validator.parts.PasswordValidator;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDateTime;

public class UserValidator {

    private final PasswordValidator passwordValidator = new PasswordValidator();

    private final EmailValidator emailValidator = EmailValidator.getInstance();
    private final AddressValidator addressValidator = new AddressValidator();
    /*language=RegExp*/
    private static final String USERNAME_REGEX = "\\w{1,20}";

    //language=RegExp
    public static final String NAME_REGEX = "^[a-zA-Z]+(([',. \\-][a-zA-Z ])?[a-zA-Z]*)*$";

    public static final String PHONE_REGEX = "\\d{9}";
    //language=RegExp
    public static final String SURNAME_REGEX = "\\w{1,20}";

    public boolean isValid(User user) {
        return validWithoutId(user) && user.getId() != null;
    }

    public boolean validWithoutId(User user) {
        if (
                user.getUsername() == null || user.getUsername().isEmpty() ||
                        user.getPassword() == null || user.getPassword().isEmpty() ||
                        user.getRole() == null ||
                        user.getName() == null || user.getName().isEmpty() ||
                        user.getCreation() == null ||
                        user.getPhone() == null || user.getPhone().isEmpty() ||
                        user.getEmail() == null
        ) {
            return false;
        }

        return passwordValidator.isValid(user.getPassword())
                && user.getUsername().matches(USERNAME_REGEX)
                && user.getName().matches(NAME_REGEX)
                && user.getPhone().matches(PHONE_REGEX)
                && emailValidator.isValid(user.getEmail())
                && (user.getSurname() == null || user.getSurname().matches(SURNAME_REGEX))
                && user.getCreation().isAfter(LocalDateTime.now())
                &&
                addressValidator.isValid(user.getStreet(),
                        user.getHouse(),
                        user.getRoom(),
                        user.getPorch(),
                        user.getFloor());
    }
}
