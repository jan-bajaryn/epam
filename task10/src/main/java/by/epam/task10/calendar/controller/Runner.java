package by.epam.task10.calendar.controller;

import by.epam.task10.calendar.entity.factory.exception.IllegalCalendarParamsException;
import by.epam.task10.calendar.entity.factory.exception.IllegalRegularDayParamsException;

import java.io.FileNotFoundException;


public class Runner {

    public static void main(String[] args) throws FileNotFoundException, IllegalRegularDayParamsException, IllegalCalendarParamsException {
        Controller controller = new Controller();
        controller.run();
    }
}
