package by.epam.task10.calendar.entity;

import by.epam.task10.calendar.entity.factory.RegularDayBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class RegularFreeCelebrity extends RepeatedFreeCelebrity implements Serializable {

    private LocalDate beginDate;
    private Period delta;
    private Direction direction;

    public static RegularDayBuilder builder() {
        return RegularDayBuilder.prototype();
    }

    public RegularFreeCelebrity(boolean celebrity,
                                boolean free,
                                String description,
                                String name,
                                LocalDate beginDate,
                                Period delta,
                                Direction direction) {
        super(celebrity, free, description, name);
        this.beginDate = beginDate;
        this.delta = delta;
        this.direction = direction;
    }

    public RegularFreeCelebrity(boolean celebrity, boolean free, String description, String name) {
        super(celebrity, free, description, name);
    }


    @Override
    public LocalDate nextDate(LocalDate localDate) {
        switch (this.direction) {
            case FORWARD:
                int compare = localDate.compareTo(this.beginDate);
                if (compare < 0) {
                    return this.beginDate;
                } else if (compare == 0) {
                    return this.beginDate.plus(delta);
                } else {
                    LocalDate afterBeginDate = findAfterBeginDate(localDate);
                    return afterBeginDate.equals(localDate) ? localDate.plus(this.delta) : afterBeginDate;
                }
            case TWO_WAYS:
                int compareTwoWays = localDate.compareTo(this.beginDate);
                if (compareTwoWays > 0) {
                    LocalDate afterBeginDate = findAfterBeginDate(localDate);
                    return afterBeginDate.equals(localDate) ? localDate.plus(this.delta) : afterBeginDate;
                } else if (compareTwoWays == 0) {
                    return beginDate.plus(delta);
                } else {
                    LocalDate beforeBegin = findBeforeBegin(localDate);
                    return beforeBegin.equals(localDate) ? localDate.plus(this.delta) : beforeBegin;
                }
            default:
                throw new IllegalArgumentException("Direction can't be null. Unexpected exception.");
        }
    }

    private LocalDate findBeforeBegin(LocalDate localDate) {
        LocalDate temp = this.beginDate;
        Period period = this.delta;
        while (temp.compareTo(localDate) > 0) {
            temp = temp.minus(period);
            period = period.plus(this.delta);
        }
        return temp;
    }

    private LocalDate findAfterBeginDate(LocalDate localDate) {
        LocalDate temp = this.beginDate;
        Period tempPeriod = this.delta;
        while (temp.compareTo(localDate) < 0) {
            temp = beginDate.plus(tempPeriod);
            tempPeriod = tempPeriod.plus(delta);
        }
        return temp;
    }

    @Override
    public boolean isThatDay(LocalDate localDate) {
        int compare = this.beginDate.compareTo(localDate);
        if (compare == 0) {
            return true;
        } else if (compare < 0) {
            return findAfterBeginDate(localDate).equals(localDate);
        } else {
            return findBeforeBegin(localDate).equals(localDate);
        }
    }


    public LocalDate getBeginDate() {
        return beginDate;
    }

    public enum Direction {
        FORWARD, TWO_WAYS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegularFreeCelebrity that = (RegularFreeCelebrity) o;

        if (beginDate != null ? !beginDate.equals(that.beginDate) : that.beginDate != null) return false;
        if (delta != null ? !delta.equals(that.delta) : that.delta != null) return false;
        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        int result = beginDate != null ? beginDate.hashCode() : 0;
        result = 31 * result + (delta != null ? delta.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


}
