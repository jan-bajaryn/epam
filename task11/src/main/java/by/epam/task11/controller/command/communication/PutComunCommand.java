package by.epam.task11.controller.command.communication;

import by.epam.task11.controller.command.dialog.Request;
import by.epam.task11.controller.command.dialog.Response;
import by.epam.task11.view.InputDataReader;

public class PutComunCommand implements CommunicationCommand {

    private InputDataReader inputDataReader = new InputDataReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        String s = inputDataReader.readFileName();
        request.setFileName(s);
        return response;
    }
}
