package by.epam.task10.calendar.entity.factory;

import by.epam.task10.calendar.entity.impl.SpecDate;
import by.epam.task10.calendar.entity.factory.exception.IllegalParamsSpecDateException;
import by.epam.task10.calendar.service.formatter.StringFormatter;
import by.epam.task10.calendar.service.validator.SpecificDateValidator;

import java.time.LocalDate;

public class SpecDateFactory {
    private StringFormatter stringFormatter = new StringFormatter();
    private SpecificDateValidator specificDateValidator = new SpecificDateValidator();

    public SpecDate create(boolean celebrity, boolean free, String description, String name, LocalDate localDate) throws IllegalParamsSpecDateException {
        description = format(description);
        name = format(name);
        SpecDate specDate = new SpecDate(celebrity, free, description, name, localDate);
        if (specificDateValidator.isValid(specDate)) {
            return specDate;
        } else {
            throw new IllegalParamsSpecDateException("Parameters to SpecDate are illegal.");
        }
    }

    private String format(String string) {
        if (string != null) {
            string = stringFormatter.formatNamDesc(string);
        }
        return string;
    }
}
