package by.epam.task10.textfile.controller.command.communication;

import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;
import by.epam.task10.textfile.view.InputDataReader;

public class AppendFileComun implements CommunicationCommand {
    private InputDataReader inputDataReader = new InputDataReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        String data = inputDataReader.readAppendData();

        request.setStringData(data);
        return response;

    }
}
