package by.epam.cafe.service.validator;

import by.epam.cafe.entity.impl.User;
import by.epam.cafe.service.validator.parts.PasswordValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class UserValidator {

    private static final Logger log = LogManager.getLogger(UserValidator.class);


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
            log.debug("Params is null");
            return false;
        }

        boolean phoneMatches = user.getPhone().matches(PHONE_REGEX);
        boolean nameMatches = user.getName().matches(NAME_REGEX);
        boolean usernameMatches = user.getUsername().matches(USERNAME_REGEX);
//        boolean passwordMatches = passwordValidator.isValid(user.getPassword());
        //TODO replace with normal realization in comments
        boolean passwordMatches = true;
        boolean emailMatches = emailValidator.isValid(user.getEmail());
        boolean surnameMatches = user.getSurname() == null || user.getSurname().matches(SURNAME_REGEX);
        boolean addressMatches = addressValidator.isValid(user.getStreet(),
                user.getHouse(),
                user.getRoom(),
                user.getPorch(),
                user.getFloor());

        logAll(phoneMatches, nameMatches, usernameMatches,
                passwordMatches, emailMatches, surnameMatches,
                addressMatches);

        return boolSum(passwordMatches, usernameMatches,
                nameMatches, phoneMatches, emailMatches,
                surnameMatches, addressMatches);
//                && user.getCreation().isAfter(LocalDateTime.now())
    }

    private boolean boolSum(boolean... booleans) {
        for (boolean ans : booleans) {
            if (!ans) {
                return false;
            }
        }
        return true;
    }

    private void logAll(boolean phoneMatches, boolean nameMatches,
                        boolean usernameMatches, boolean passwordMatches,
                        boolean emailMatches, boolean surnameMatches,
                        boolean addressMatches) {
        log.debug("logAll:  phoneMatches = {}", phoneMatches);
        log.debug("logAll:   nameMatches = {}", nameMatches);
        log.debug("logAll:   usernameMatches = {}", usernameMatches);
        log.debug("logAll:   passwordMatches = {}", passwordMatches);
        log.debug("logAll:   emailMatches = {}", emailMatches);
        log.debug("logAll:   surnameMatches = {}", surnameMatches);
        log.debug("logAll:   addressMatches = {}", addressMatches);
    }
}
