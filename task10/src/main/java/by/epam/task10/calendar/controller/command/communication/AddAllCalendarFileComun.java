package by.epam.task10.calendar.controller.command.communication;

import by.epam.task10.calendar.controller.command.dialog.Request;
import by.epam.task10.calendar.controller.command.dialog.Response;
import by.epam.task10.calendar.view.InputDataReader;

public class AddAllCalendarFileComun implements CommunicationCommand {

    InputDataReader inputDataReader = new InputDataReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        request.setFileName(inputDataReader.readFileName());
        response.setNextRequest(request);
        return response;
    }

}
