package by.epam.learn.task2.date.domain;

public class Month {
    int number;
    int dayCount;

    public Month(int number, int dayCount) {
        this.number = number;
        this.dayCount = dayCount;
    }

    public Month() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }
}
