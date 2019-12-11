package by.epam.learn.inclass.controller;

import by.epam.learn.inclass.domain.Date;

import java.util.Map;

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
