package by.epam.task10.calendar.entity;


import java.time.LocalDate;

public abstract class FreeCelebrity {
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

}
