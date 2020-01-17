package by.epam.task10.calendar.controller.command.communication;

import by.epam.task10.calendar.view.InputDataReader;
import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;

public class AddIrregularDateComun implements CommunicationCommand {
    private InputDataReader inputDataReader = new InputDataReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        request.setIndex(-1);
        try {
            request.setIndex(inputDataReader.readIndex());
        } catch (Exception e) {
        }
        return response;
    }
}
