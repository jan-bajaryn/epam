package by.epam.task10.calendar.view.communication;

import by.epam.task10.calendar.entity.Calendar;

public class Request {
    private Calendar calendar;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
