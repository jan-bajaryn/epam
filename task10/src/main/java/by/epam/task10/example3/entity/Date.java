package by.epam.task10.example3.entity;

public class Date implements Comparable<Date> {

    public static final int MIN_DAY = 1;
    public static final int FEBRUARY_LEAP_YEAR = 29;
    int year;
    int month;
    int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }


    public Date nextDay() {
        return nextDay(this.day, this.month, this.day);
    }

    public Date nextMonth() {
        return nextMonth(this.day, this.month, this.year);
    }

    public Date nextYear() {
        return nextYear(this.day, this.month, this.year);
    }

    private Date nextDay(int oldDay, int oldMonth, int oldYear) {
        int newDay = oldDay + 1;
        if (newDay > Month.values()[oldMonth - 1].getDayCount()) {
            if (isLeap(oldYear) && oldMonth == Month.FEBRUARY.getNumber()) {
                if (newDay > FEBRUARY_LEAP_YEAR) {
                    newDay = MIN_DAY;
                    return nextMonth(newDay, oldMonth, oldYear);
                }
            } else {
                newDay = MIN_DAY;
                return nextMonth(newDay, oldMonth, oldYear);
            }
        }
        return new Date(newDay, oldMonth, oldYear);
    }

    private Date nextMonth(int newDay, int newMonth, int newYear) {
        newMonth++;
        if (newMonth > Month.values().length) {
            newMonth = 1;
            return nextYear(newDay, newMonth, newYear);
        }
        return new Date(newDay, newMonth, newYear);
    }

    private Date nextYear(int newDay, int newMonth, int newYear) {
        newYear++;
        return new Date(newDay, newMonth, newYear);
    }

    public boolean isLeap(int year) {
        if (year % 400 == 0)
            return false;
        return year % 4 == 0;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date date = (Date) o;

        if (year != date.year) return false;
        if (month != date.month) return false;
        return day == date.day;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + month;
        result = 31 * result + day;
        return result;
    }

    @Override
    public String toString() {
        return "Date{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public int compareTo(Date o) {
        if (o == null) {
            return 1;
        }
        if (year > o.year) {
            return 1;
        } else if (year < o.year) {
            return -1;
        } else if (month > o.month) {
            return 1;
        } else if (month < o.month) {
            return -1;
        } else return Integer.compare(day, o.day);
    }
}
