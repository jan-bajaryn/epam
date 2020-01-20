package by.epam.task10.shop.controller.command;


import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;

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
