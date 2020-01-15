package by.epam.task10.calendar.entity.dto;

import java.time.LocalDate;

public class SpecDateDTO {
    private LocalDate date;
    private boolean celebrity;
    private boolean free;
    private String description;
    private String name;

    public SpecDateDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
}
