package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;

public class ExitExecCommand implements ExecCommand {
    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setStatus(Response.EXIT);
        return response;
    }

    @Override
    public String definition() {
        return "Exit the application.";
    }
}
