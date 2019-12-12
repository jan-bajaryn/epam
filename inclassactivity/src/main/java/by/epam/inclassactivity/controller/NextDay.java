package by.epam.inclassactivity.controller;


import by.epam.inclassactivity.domain.Date;

public class NextDay {
    private Date date;

    public NextDay(Date date) {
        this.date = date;
    }

    public Date execute(){
        date = date.nextDay(date);
        return date;
    }
}
