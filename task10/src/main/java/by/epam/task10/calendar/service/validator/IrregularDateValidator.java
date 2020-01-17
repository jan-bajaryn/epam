package by.epam.task10.calendar.service.validator;

import by.epam.task10.calendar.entity.IrregularFreeCelebrity;

import java.util.regex.Pattern;

public class IrregularDateValidator {
    private Pattern pattern = Pattern.compile("(\\w+\\s*)*");

    public boolean isValid(IrregularFreeCelebrity irregularFreeCelebrity) {
        return irregularFreeCelebrity != null &&
                !irregularFreeCelebrity.getDescription().contains("\n") &&
                pattern.matcher(irregularFreeCelebrity.getName()).matches();
    }
}
