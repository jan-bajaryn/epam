package by.epam.task12.controller.command.communication;

import by.epam.task12.controller.command.dialog.Request;
import by.epam.task12.controller.command.dialog.Response;
import by.epam.task12.view.InputDataReader;

public class FillSemaphoreComun implements CommunicationCommand {
    private InputDataReader inputDataReader = new InputDataReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        request.setFileNameMatrix(inputDataReader.readFileName("matrix"));
        request.setFileNameArr(inputDataReader.readFileName("array"));

        return response;
    }
}
