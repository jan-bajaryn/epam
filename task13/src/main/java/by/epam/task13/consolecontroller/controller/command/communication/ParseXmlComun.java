package by.epam.task13.consolecontroller.controller.command.communication;


import by.epam.task13.consolecontroller.controller.command.dialog.Request;
import by.epam.task13.consolecontroller.controller.command.dialog.Response;
import by.epam.task13.view.InputDataReader;

public class ParseXmlComun implements CommunicationCommand {
    private InputDataReader inputDataReader = new InputDataReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        request.setFileNameMatrix(inputDataReader.readFileName());
        request.setMethod(inputDataReader.readMethod());
        return response;
    }
}
