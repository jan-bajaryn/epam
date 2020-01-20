package by.epam.task10.calendar.controller.command.communication;

import by.epam.task10.calendar.view.InputDataReader;
import by.epam.task10.calendar.controller.command.dialog.Request;
import by.epam.task10.calendar.controller.command.dialog.Response;

import java.util.ArrayList;
import java.util.List;

public class AddSpecDateComun implements CommunicationCommand {
    private InputDataReader inputDataReader = new InputDataReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        request.setParams(new ArrayList<>());
        try{
            List<Object> params = inputDataReader.readSpecDateParams();
            request.setParams(params);
        }catch (Exception e){}

        return response;
    }
}
