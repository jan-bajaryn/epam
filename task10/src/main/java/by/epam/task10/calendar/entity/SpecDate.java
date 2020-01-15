package by.epam.task10.calendar.entity;

import java.time.LocalDate;

public class SpecDate extends FreeCelebrity {
    private LocalDate date;

    public SpecDate(boolean celebrity, boolean free, String description, String name, LocalDate localDate) {
        super(celebrity, free, description, name);
        this.date = localDate;
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
}
