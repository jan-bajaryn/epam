package by.epam.task10.calendar.entity.impl;

import by.epam.task10.calendar.entity.FreeCelebrity;

import java.io.Serializable;
import java.time.LocalDate;

public class SpecDate extends FreeCelebrity implements Serializable {
    private LocalDate date;

    public SpecDate() {
    }

    public SpecDate(boolean celebrity, boolean free, String description, String name, LocalDate localDate) {
        super(celebrity, free, description, name);
        this.date = localDate;
    }

    public SpecDate(boolean celebrity, boolean free, String description, String name) {
        super(celebrity, free, description, name);
    }

    @Override
    public boolean isThatDay(LocalDate localDate) {
        return this.date.equals(localDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecDate specDate = (SpecDate) o;

        return date != null ? date.equals(specDate.date) : specDate.date == null;
    }

    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
