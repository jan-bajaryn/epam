package by.epam.learn.inclass.validator;

import by.epam.learn.inclass.controller.LeaptYearAdapter;
import by.epam.learn.inclass.domain.Date;

public class Validator {


    LeaptYearAdapter leaptYearAdapter;

    public Validator() {
        leaptYearAdapter = new LeaptYearAdapter();
    }

    public boolean isValid(String[] data) {
        int year = 0;
        int month = 0;
        int day = 0;
        try {
            year = Integer.parseInt(data[0]);
            month = Integer.parseInt(data[1]);
            day = Integer.parseInt(data[2]);
        } catch (Exception e) {
            return false;
        }
        if (year > 0 && month > 0 && month < 13 && day > 0) {
            if (month == 2 && leaptYearAdapter.isLeap(year) && day < 30) {
                return true;
            }
            return Date.months.get(month - 1).getDayCount() >= day;
        }
        return false;
    }
}
