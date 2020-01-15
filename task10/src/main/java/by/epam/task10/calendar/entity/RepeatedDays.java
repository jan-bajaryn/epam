package by.epam.task10.calendar.entity;

import java.time.LocalDate;

public abstract class RepeatedDays extends FreeCelebrity {
    public RepeatedDays(boolean celebrity, boolean free, String description, String name) {
        super(celebrity, free, description, name);
    }

    public abstract LocalDate nextDate(LocalDate localDate);

}
