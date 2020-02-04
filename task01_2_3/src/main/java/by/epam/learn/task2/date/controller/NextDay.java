package by.epam.learn.task2.date.controller;


import by.epam.learn.task2.date.entity.Date;

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
