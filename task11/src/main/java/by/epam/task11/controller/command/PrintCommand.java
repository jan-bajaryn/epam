package by.epam.task11.controller.command;

import by.epam.task11.controller.command.dialog.Request;
import by.epam.task11.controller.command.dialog.Response;

public class PrintCommand implements by.epam.task11.controller.ExecCommand {
    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (request.getComposite() != null) {
            String operation = request.getComposite().operation();
            response.setDisplayInformation(operation);
            return response;
        }
        response.setDisplayInformation("There no composite. Please enter information from file.");
        return response;
    }

    @Override
    public String definition() {
        return "Print existing text to console.";
    }
}
