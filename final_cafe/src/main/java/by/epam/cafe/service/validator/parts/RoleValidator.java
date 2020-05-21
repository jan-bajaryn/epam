package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate {@link Role}
 */
public class RoleValidator implements Validator<Role> {
    @Override
    public boolean isValid(Role input) {
        return input != null && input != Role.ANON;
    }
}
