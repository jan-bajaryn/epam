package by.epam.task10.textfile.controller.command;

import by.epam.task10.textfile.entity.FFile;
import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;
import by.epam.task10.textfile.service.FileWriter;
import by.epam.task10.textfile.service.InOutException;
import by.epam.task10.textfile.view.communication.FileNotExistsWrongInput;
import by.epam.task10.textfile.view.communication.NullAppendDataWrongInput;
import by.epam.task10.textfile.view.communication.NullFFileWrongInput;

public class AppendDataCommand implements ExecCommand {
    private FileWriter fileWriter = new FileWriter();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        String data = request.getStringData();
        FFile fFile = request.getfFile();
        if (fFile == null) {
            response.setWrongInput(new NullFFileWrongInput());
            return response;
        }
        if (data == null) {
            response.setWrongInput(new NullAppendDataWrongInput());
            return response;
        }

        try {
            fileWriter.append(fFile, "\n" + data);
        } catch (InOutException e) {
            response.setWrongInput(new FileNotExistsWrongInput());
        }

        return response;
    }

    @Override
    public String definition() {
        return "Append data to file with existing signature.";
    }
}
