package by.epam.task10.example3.service.validator;

import by.epam.task10.example3.entity.Date;
import by.epam.task10.example3.entity.Month;

public class DateValidator {
    public boolean isValid(Date date) {
        if (date.getYear() > 0 && date.getMonth() > 0 && date.getMonth() < 13 && date.getDay() > 0) {
            if (date.getMonth() == Month.FEBRUARY.getNumber() && date.isLeap(date.getYear()) && date.getDay() < 30) {
                return true;
            }
            return Month.values()[date.getMonth() - 1].getDayCount() >= date.getDay();
        }
        return false;
    }
}
