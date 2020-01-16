package by.epam.task10.calendar.entity;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class RepeatedFreeCelebrity extends FreeCelebrity implements Serializable {
    public RepeatedFreeCelebrity(boolean celebrity, boolean free, String description, String name) {
        super(celebrity, free, description, name);
    }

    public abstract LocalDate nextDate(LocalDate localDate);

}
