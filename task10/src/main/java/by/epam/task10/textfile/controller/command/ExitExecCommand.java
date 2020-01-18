package by.epam.task10.textfile.controller.command;


import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;

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
