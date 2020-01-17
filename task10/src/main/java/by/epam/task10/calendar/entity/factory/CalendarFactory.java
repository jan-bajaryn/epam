package by.epam.task10.calendar.entity.factory;

import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.entity.IrregularFreeCelebrity;
import by.epam.task10.calendar.entity.impl.RegularFreeCelebrity;
import by.epam.task10.calendar.entity.impl.SpecDate;
import by.epam.task10.calendar.entity.factory.exception.IllegalCalendarParamsException;
import by.epam.task10.calendar.service.formatter.CalendarArgsFormatter;
import by.epam.task10.calendar.service.validator.CalendarValidator;

import java.util.Set;

public class CalendarFactory {
    private CalendarValidator calendarValidator = new CalendarValidator();
    private CalendarArgsFormatter calendarArgsFormatter = new CalendarArgsFormatter();

    public Calendar create(String name,
                           Set<RegularFreeCelebrity> regularFreeCelebrities,
                           Set<SpecDate> specificDates,
                           Set<IrregularFreeCelebrity> irregularDays) throws IllegalCalendarParamsException {
        Calendar calendar = new Calendar(regularFreeCelebrities, specificDates, irregularDays);
        calendar.setName(name);
        Calendar.FreeCelebrityManager manager = calendar.getManager();

        Set<RegularFreeCelebrity> regularDays = manager.getRegularDays();
        Set<SpecDate> specificDays = manager.getSpecificDays();

        calendarArgsFormatter.format(regularDays, specificDays);

        if (calendarValidator.isValid(calendar)) {
            return calendar;
        }
        throw new IllegalCalendarParamsException("Parameters of calendar are illegal.");
    }

}