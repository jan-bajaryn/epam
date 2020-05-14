package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.service.Validator;

public class RoleValidator implements Validator<Role> {
    @Override
    public boolean isValid(Role input) {
        return input!=null;
    }
}
