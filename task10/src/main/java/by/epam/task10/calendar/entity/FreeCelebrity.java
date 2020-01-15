package by.epam.task10.calendar.entity;


import java.io.Serializable;
import java.time.LocalDate;

public abstract class FreeCelebrity implements Serializable {
    public static final String NONE = "";

    private boolean celebrity;
    private boolean free;
    private String description;
    private String name;

    public FreeCelebrity(boolean celebrity, boolean free, String description, String name) {
        this.celebrity = celebrity;
        this.free = free;
        this.description = description;
        this.name = name;
    }

    public FreeCelebrity() {
    }

    public abstract boolean isThatDay(LocalDate localDate);

    public boolean isCelebrity() {
        return celebrity;
    }

    public boolean isFree() {
        return free;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setCelebrity(boolean celebrity) {
        this.celebrity = celebrity;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

}
