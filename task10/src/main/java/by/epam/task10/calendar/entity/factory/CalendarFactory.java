package by.epam.task10.calendar.entity.factory;

import by.epam.task10.calendar.dao.Calendar;
import by.epam.task10.calendar.entity.IrregularDay;
import by.epam.task10.calendar.entity.SpecDate;
import by.epam.task10.calendar.entity.factory.exception.IllegalCalendarParamsException;
import by.epam.task10.calendar.service.validator.CalendarValidator;

import java.util.List;

public class CalendarFactory {
    private CalendarValidator calendarValidator = new CalendarValidator();

    public Calendar create(List<SpecDate> regularDays,
                           List<SpecDate> specificDates,
                           List<IrregularDay> irregularDays) throws IllegalCalendarParamsException {
        Calendar calendar = new Calendar();
        if (calendarValidator.isValid(calendar)) {
            return calendar;
        }
        throw new IllegalCalendarParamsException("Parameters of calendar are illegal.");
    }
}