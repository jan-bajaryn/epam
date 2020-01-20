package by.epam.task10.shop.controller.command;

import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.service.StateFormatter;

public class PrintStateCommand implements ExecCommand {
    private StateFormatter stateFormatter = new StateFormatter();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        response.setDisplayInformation(stateFormatter.format());
        return response;
    }

    @Override
    public String definition() {
        return "Print current state";
    }
}
