package by.epam.cafe.service.validator.parts;

import by.epam.cafe.service.Validator;

import java.time.LocalDateTime;

public class TimeValidator implements Validator<LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime input) {
        return input != null && input.compareTo(LocalDateTime.now()) > 0;
    }
}
