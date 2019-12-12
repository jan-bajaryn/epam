package by.epam.inclassactivity.controller;

public class LeaptYearAdapter {
    public LeaptYearAdapter() {
    }

    public boolean isLeap(int year) {
        if (year % 400 == 0)
            return false;
        return year % 4 == 0;
    }
}
