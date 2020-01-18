package by.epam.task10.textfile.controller.command.communication;

import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;
import by.epam.task10.textfile.view.InputDataReader;

import java.util.ArrayList;
import java.util.List;

public class ChangeSignatureComun implements CommunicationCommand {
    private InputDataReader inputDataReader = new InputDataReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        List<Object> list=new ArrayList<>();
        try{
            list = inputDataReader.readCreationData();
        }catch (Exception e){
        }

        request.setData(list);
        return response;
    }
}
