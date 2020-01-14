package by.epam.task10.example3.service.validator;

import by.epam.task10.example3.entity.Calendar;

public class CalendarValidator {

    public boolean isValid(Calendar calendar) {
        if (calendar == null) {
            return false;
        }

        return true;
    }
}
