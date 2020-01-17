package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.dao.XMLFileReader;
import by.epam.task10.calendar.entity.*;
import by.epam.task10.calendar.entity.factory.CalendarFactory;
import by.epam.task10.calendar.entity.factory.exception.IllegalCalendarParamsException;
import by.epam.task10.calendar.view.communication.FileNotFoundWrongInput;
import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;
import by.epam.task10.calendar.view.communication.WrongDataCalendar;
import by.epam.task10.calendar.view.communication.NullFileNameWrongInput;
import by.epam.task10.calendar.service.CalendarService;
import by.epam.task10.calendar.service.validator.CalendarValidator;

import java.io.FileNotFoundException;
import java.util.HashSet;

public class AddAllCalendarFile implements ExecCommand {

    private XMLFileReader xmlFileReader = new XMLFileReader();
    private CalendarService calendarService = new CalendarService();
    private CalendarValidator calendarValidator = new CalendarValidator();
    private CalendarFactory calendarFactory = new CalendarFactory();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        Calendar calendar = request.getCalendar();
        String fileName = request.getFileName();
        if (fileName == null) {
            response.setWrongInput(new NullFileNameWrongInput());
            return response;
        }
        try {
            if (calendar == null) {
                calendar = calendarFactory.create("", new HashSet<>(), new HashSet<>(), new HashSet<>());
            }
            Calendar calToAppend = xmlFileReader.xmlToCalendar(fileName);
            if (calendarValidator.isValid(calToAppend)) {
                calendarService.appendCalendar(calendar, calToAppend);
                request.setCalendar(calendar);
                response.setNextRequest(request);
            } else {
                System.err.println("not valid calendar from xml");
                response.setNextRequest(request);
                response.setWrongInput(new WrongDataCalendar());
            }
            return response;
        } catch (FileNotFoundException e) {
            System.err.println("exception thrown");
            response.setWrongInput(new FileNotFoundWrongInput());
            return response;
        } catch (IllegalCalendarParamsException e) {
            return response;
        }
    }

    @Override
    public String definition() {
        return "Add all data about special days to your calendar.";
    }
}
