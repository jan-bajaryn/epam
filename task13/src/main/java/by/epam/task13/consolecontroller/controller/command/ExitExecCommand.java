package by.epam.task13.consolecontroller.controller.command;


import by.epam.task13.consolecontroller.controller.Controller;
import by.epam.task13.consolecontroller.controller.command.dialog.Request;
import by.epam.task13.consolecontroller.controller.command.dialog.Response;

import java.util.ResourceBundle;

public class ExitExecCommand implements ExecCommand {
    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setStatus(Response.EXIT);
        return response;
    }

    @Override
    public String definition() {

        ResourceBundle rb = ResourceBundle.getBundle("property.text", Controller.locale);
        return rb.getString("consoleinput.exit");
    }
}
