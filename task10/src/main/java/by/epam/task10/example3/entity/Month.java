package by.epam.task10.example3.entity;

public enum Month {

    JANUARY("январь", 1, 31),
    FEBRUARY("февраль", 2, 28),
    MARCH("март", 3, 31),
    APRIL("Апрель", 4, 30),
    MAY("май", 5, 31),
    JUNE("июнь", 6, 30),
    JULY("июль", 7, 31),
    AUGUST("август", 8, 31),
    SEPTEMBER("сентябль", 9, 30),
    OCTOBER("октябрь", 10, 31),
    NOVEMBER("ноябрь", 11, 30),
    DECEMBER("декабрь", 12, 31);

    private String monthName;
    private int number;
    private int dayCount;

    Month(String monthName, int number, int dayCount) {
        this.monthName = monthName;
        this.number = number;
        this.dayCount = dayCount;
    }

    public int getNumber() {
        return number;
    }

    public int getDayCount() {
        return dayCount;
    }

    public String getMonthName() {
        return monthName;
    }
}
