package by.epam.task10.calendar.controller.command.communication;

import by.epam.task10.calendar.view.InputDataReader;
import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;

import java.util.ArrayList;
import java.util.List;

public class AddRegularDateComun implements CommunicationCommand {
    private InputDataReader inputDataReader = new InputDataReader();

    //InputMismatchException
    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        List<Object> params = new ArrayList<>();
        try {
            params = inputDataReader.readRegularDayParams();
        } catch (Exception e) {
        }
        request.setParams(params);
        return response;
    }
}
