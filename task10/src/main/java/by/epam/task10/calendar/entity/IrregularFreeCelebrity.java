package by.epam.task10.calendar.entity;


import java.io.Serializable;

public abstract class IrregularFreeCelebrity extends FreeCelebrity implements Serializable {

    public IrregularFreeCelebrity(boolean celebrity, boolean free, String description, String name) {
        super(celebrity, free, description, name);
    }

    public IrregularFreeCelebrity() {
    }
}
