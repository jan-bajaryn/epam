package by.epam.task10.calendar.entity;

public abstract class IrregularDay extends RepeatedDays {

    public IrregularDay(boolean celebrity, boolean free, String description, String name) {
        super(celebrity, free, description, name);
    }
}
