package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.entity.factory.CalendarFactory;
import by.epam.task10.calendar.entity.factory.exception.IllegalCalendarParamsException;
import by.epam.task10.calendar.view.communication.IllegalCalendarNameWrongInput;
import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;

import java.util.HashSet;

public class CreateEmptyCalendarCommand implements ExecCommand {

    private CalendarFactory calendarFactory = new CalendarFactory();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        try {
            String calendarName = request.getCalendarName();
            Calendar calendar = calendarFactory.create(calendarName, new HashSet<>(), new HashSet<>(), new HashSet<>());
            request.setCalendar(calendar);
        } catch (IllegalCalendarParamsException e) {
            response.setWrongInput(new IllegalCalendarNameWrongInput());
        }
        return response;
    }

    @Override
    public String definition() {
        return "Create new empty calendar with deleting previous (make sure you saved what you want to use in the future).";
    }
}
