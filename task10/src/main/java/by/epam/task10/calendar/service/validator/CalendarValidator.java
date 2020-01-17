package by.epam.task10.calendar.service.validator;

import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.entity.IrregularFreeCelebrity;
import by.epam.task10.calendar.entity.impl.RegularFreeCelebrity;
import by.epam.task10.calendar.entity.impl.SpecDate;

import java.util.Set;
import java.util.regex.Pattern;

public class CalendarValidator {
    private RegularDayValidator regularDayValidator = new RegularDayValidator();
    private SpecificDateValidator specificDateValidator = new SpecificDateValidator();
    private IrregularDateValidator irregularDateValidator = new IrregularDateValidator();
    private Pattern pattern = Pattern.compile("(\\w+\\s*)*");

    public boolean isValid(Calendar calendar) {
        if (calendar == null ||
                calendar.getManager().getSpecificDays() == null ||
                calendar.getManager().getIrregularDays() == null ||
                calendar.getManager().getRegularDays() == null ||
                !pattern.matcher(calendar.getName()).matches()
        ) {
            return false;
        }

        Set<RegularFreeCelebrity> regularDays = calendar.getManager().getRegularDays();
        Set<IrregularFreeCelebrity> irregularDays = calendar.getManager().getIrregularDays();
        Set<SpecDate> specificDays = calendar.getManager().getSpecificDays();
        for (RegularFreeCelebrity regularDay : regularDays) {
            if (!regularDayValidator.isValid(regularDay)) {
                return false;
            }
        }
        for (SpecDate specDate : specificDays) {
            if (!specificDateValidator.isValid(specDate)) {
                return false;
            }
        }
        for (IrregularFreeCelebrity irregularDay : irregularDays) {
            if (!irregularDateValidator.isValid(irregularDay)) {
                return false;
            }
        }
        return true;
    }
}
