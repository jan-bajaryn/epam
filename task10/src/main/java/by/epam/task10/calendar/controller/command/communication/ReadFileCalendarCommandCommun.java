package by.epam.task10.calendar.controller.command.communication;

import by.epam.task10.calendar.view.InputDataReader;
import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;

public class ReadFileCalendarCommandCommun implements CommunicationCommand {
    private InputDataReader inputDataReader = new InputDataReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        request.setFileName(inputDataReader.readFileName());
        response.setNextRequest(request);
        return response;
    }
}
