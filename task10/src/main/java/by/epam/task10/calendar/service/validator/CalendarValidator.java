package by.epam.task10.calendar.service.validator;

import by.epam.task10.calendar.dao.Calendar;

public class CalendarValidator {

    public boolean isValid(Calendar calendar) {
        if (calendar == null) {
            return false;
        }

        return true;
    }
}
