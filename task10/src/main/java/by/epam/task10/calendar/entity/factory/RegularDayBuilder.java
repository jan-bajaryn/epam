package by.epam.task10.calendar.entity.factory;

import by.epam.task10.calendar.entity.FreeCelebrity;
import by.epam.task10.calendar.entity.RegularFreeCelebrity;
import by.epam.task10.calendar.entity.factory.exception.IllegalRegularDayParamsException;
import by.epam.task10.calendar.service.validator.RegularDayValidator;

import java.time.LocalDate;
import java.time.Period;

public class RegularDayBuilder {

    private RegularDayValidator regularDayValidator = new RegularDayValidator();

    private boolean celebrity;
    private boolean free;
    private String description = FreeCelebrity.NONE;
    private String name = FreeCelebrity.NONE;
    private LocalDate beginDate;
    private Period delta;
    private RegularFreeCelebrity.Direction direction;

    private RegularDayBuilder() {
    }

    public static RegularDayBuilder prototype() {
        return new RegularDayBuilder();
    }

    public RegularFreeCelebrity build() throws IllegalRegularDayParamsException {
        RegularFreeCelebrity regularDay = new RegularFreeCelebrity(celebrity, free, description, name, beginDate, delta, direction);
        if (regularDayValidator.isValid(regularDay)) {
            return regularDay;
        }
        throw new IllegalRegularDayParamsException("Parameters of regular day are illegal.");
    }

    public RegularDayBuilder celebrity(boolean celebrity) {
        this.celebrity = celebrity;
        return this;
    }

    public RegularDayBuilder free(boolean free) {
        this.free = free;
        return this;
    }

    public RegularDayBuilder description(String description) {
        this.description = description;
        return this;
    }

    public RegularDayBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RegularDayBuilder beginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
        return this;
    }

    public RegularDayBuilder delta(Period delta) {
        this.delta = delta;
        return this;
    }

    public RegularDayBuilder direction(RegularFreeCelebrity.Direction direction) {
        this.direction = direction;
        return this;
    }


}
