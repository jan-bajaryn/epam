package by.epam.task10.textfile.controller.command.communication;

import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;
import by.epam.task10.textfile.view.InputDataReader;

public class RenameFileComun implements CommunicationCommand {
    private InputDataReader inputDataReader = new InputDataReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        String fileName = inputDataReader.readFileName();
        request.setStringData(fileName);
        return response;
    }
}
