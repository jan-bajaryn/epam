package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.Controller;
import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.controller.command.dialog.Request;
import by.epam.task10.calendar.controller.command.dialog.Response;
import by.epam.task10.calendar.entity.Calendar;

public class AddIrregularDateCommand implements ExecCommand {

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        Calendar calendar = request.getCalendar();
        if (calendar == null) {
            response.setDisplayInformation("For this command you need to create or read calendar at first.");
            return response;
        }
        try {
            calendar.getManager().getIrregularDays().add(Controller.irregularDates.get(request.getIndex()));
        } catch (Exception e) {
            response.setDisplayInformation("Wrong params for Irregular date.");
        }
        return response;
    }

    @Override
    public String definition() {
        return "Add irregular date to calendar";
    }
}
