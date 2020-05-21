package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.validator.Validator;

/**
 * Dedicated to validate room
 */
public class RoomValidator implements Validator<String> {
    @Override
    public boolean isValid(String input) {
        return true;
    }
}
