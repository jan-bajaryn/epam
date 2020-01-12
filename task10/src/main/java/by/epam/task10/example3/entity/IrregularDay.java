package by.epam.task10.example3.entity;

public abstract class IrregularDay extends FreeCelebrity {
    public IrregularDay(boolean selebrity, boolean free) {
        super(selebrity, free);
    }

    public abstract Date nextAfterThis(Date date);

    public abstract boolean isToday(Date date);
}
