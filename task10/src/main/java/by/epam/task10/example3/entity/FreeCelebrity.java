package by.epam.task10.example3.entity;


import java.util.Optional;

public abstract class FreeCelebrity {
    public static final String NONE = "";

    private boolean selebrity;
    private boolean free;
    private String description;
    private String name;

    public FreeCelebrity(boolean selebrity, boolean free) {
        this.selebrity = selebrity;
        this.free = free;
    }

    public boolean isSelebrity() {
        return selebrity;
    }

    public boolean isFree() {
        return free;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
