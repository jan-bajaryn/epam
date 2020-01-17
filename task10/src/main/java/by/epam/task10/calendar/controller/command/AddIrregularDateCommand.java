package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.Controller;
import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.view.communication.NullCalendarWrongImput;
import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;
import by.epam.task10.calendar.view.communication.WrongParamsIrregularDate;

public class AddIrregularDateCommand implements ExecCommand {

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        Calendar calendar = request.getCalendar();
        if (calendar == null) {
            response.setWrongInput(new NullCalendarWrongImput());
            return response;
        }
        try {
            calendar.getManager().getIrregularDays().add(Controller.irregularDates.get(request.getIndex()));
        } catch (Exception e) {
            response.setWrongInput(new WrongParamsIrregularDate());
        }
        return response;
    }

    @Override
    public String definition() {
        return "Add irregular date to calendar";
    }
}
