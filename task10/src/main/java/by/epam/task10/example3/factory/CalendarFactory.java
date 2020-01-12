package by.epam.task10.example3.factory;

import by.epam.task10.example3.entity.Calendar;
import by.epam.task10.example3.entity.IrregularDay;
import by.epam.task10.example3.validator.CalendarValidator;

import java.util.List;

public class CalendarFactory {
    private CalendarValidator calendarValidator = new CalendarValidator();
    public Calendar create(List<Calendar.SpecDate> regularDays,
                           List<Calendar.SpecDate> specificDates,
                           List<IrregularDay> irregularDays) throws IllegalCalendarParamsException {
        Calendar calendar = new Calendar(regularDays,specificDates,irregularDays);
        if (calendarValidator.isValid(calendar)){
            return calendar;
        }
        throw new IllegalCalendarParamsException("Parameters of calendar are illegal.");
    }
}
