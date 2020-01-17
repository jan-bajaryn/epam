package by.epam.task10.calendar.controller.command.communication;

import by.epam.task10.calendar.view.InputDataReader;
import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;

public class CreateEmptyCalendarCommandComun implements CommunicationCommand {

    private InputDataReader inputDataReader = new InputDataReader();


    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        request.setCalendarName(inputDataReader.readCalendarName());
        return response;
    }
}
