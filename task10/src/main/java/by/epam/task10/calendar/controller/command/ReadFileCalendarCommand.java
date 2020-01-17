package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.dao.XMLFileReader;
import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.entity.factory.exception.IllegalCalendarParamsException;
import by.epam.task10.calendar.service.validator.CalendarValidator;
import by.epam.task10.calendar.view.InputDataReader;
import by.epam.task10.calendar.view.communication.*;

import java.io.FileNotFoundException;
import java.util.HashSet;

public class ReadFileCalendarCommand implements ExecCommand {
    private XMLFileReader xmlFileReader = new XMLFileReader();
    private CalendarValidator calendarValidator = new CalendarValidator();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        String fileName = request.getFileName();
        if (fileName == null) {
            response.setWrongInput(new NullFileNameWrongInput());
            return response;
        }
        try {
            Calendar calendar = xmlFileReader.xmlToCalendar(fileName);
            if (calendarValidator.isValid(calendar)) {
                request.setCalendar(calendar);
            } else {
                System.err.println("not valid calendar from xml");
                response.setWrongInput(new WrongDataCalendar());
            }
            return response;
        } catch (FileNotFoundException e) {
            System.err.println("exception thrown");
            response.setWrongInput(new FileNotFoundWrongInput());
            return response;
        }

    }

    @Override
    public String definition() {
        return "Replace existing calendar with new from file.";
    }
}
