package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.service.parser.parts.ParamsParser;
import by.epam.cafe.service.validator.parts.RoleValidator;

/**
 * Dedicated to parse String to {@link Role} value
 */
public class RoleParser extends ParamsParser<Role> {

    private static final RoleValidator VALIDATOR = new RoleValidator();

    public RoleParser() {
        super(VALIDATOR);
    }

    @Override
    protected Role modify(String input) throws Exception {
        return Role.valueOf(input);
    }
}
