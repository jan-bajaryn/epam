package by.epam.cafe.service.validator.parts;

import by.epam.cafe.entity.enums.Role;

public class RoleValidator implements by.epam.cafe.service.validator.Validator<by.epam.cafe.entity.enums.Role> {
    @Override
    public boolean isValid(Role input) {
        return input!=null;
    }
}
