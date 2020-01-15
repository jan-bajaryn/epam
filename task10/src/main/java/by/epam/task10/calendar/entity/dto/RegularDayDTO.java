package by.epam.task10.calendar.entity.dto;

import by.epam.task10.calendar.entity.RegularDay;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class RegularDayDTO implements Serializable {
    private boolean celebrity;
    private boolean free;
    private String description;
    private String name;
    private LocalDate beginDate;
    private Period delta;
    private RegularDay.Direction direction;

    public RegularDayDTO() {
    }

    public boolean isCelebrity() {
        return celebrity;
    }

    public void setCelebrity(boolean celebrity) {
        this.celebrity = celebrity;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public Period getDelta() {
        return delta;
    }

    public void setDelta(Period delta) {
        this.delta = delta;
    }

    public RegularDay.Direction getDirection() {
        return direction;
    }

    public void setDirection(RegularDay.Direction direction) {
        this.direction = direction;
    }
}
