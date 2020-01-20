package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.controller.command.dialog.Request;
import by.epam.task10.calendar.controller.command.dialog.Response;
import by.epam.task10.calendar.dao.XMLFileReader;
import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.entity.factory.CalendarFactory;
import by.epam.task10.calendar.entity.factory.exception.IllegalCalendarParamsException;
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
            response.setDisplayInformation("File name can't be null.");
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
                response.setDisplayInformation("Format of data in this file is illegal. Please choose another file.");
            }
            return response;
        } catch (FileNotFoundException e) {
            System.err.println("exception thrown");
            response.setDisplayInformation("There not so file found. Please choose another file name.");
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
