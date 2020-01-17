package by.epam.task10.calendar.service.validator;

import by.epam.task10.calendar.entity.impl.SpecDate;

import java.util.regex.Pattern;

public class SpecificDateValidator {
    private Pattern pattern = Pattern.compile("(\\w+\\s*)*");

    public boolean isValid(SpecDate specDate) {
        return specDate != null &&
                pattern.matcher(specDate.getName()).matches() &&
                !specDate.getDescription().contains("\n");
    }
}
