package by.epam.task10.textfile.controller.command;

import by.epam.task10.textfile.entity.FFile;
import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;
import by.epam.task10.textfile.service.FileWriter;
import by.epam.task10.textfile.service.InOutException;
import by.epam.task10.textfile.view.communication.FileExistsWrongInput;
import by.epam.task10.textfile.view.communication.NullFFileWrongInput;

public class CreateFileCommand implements ExecCommand {
    private FileWriter fileWriter = new FileWriter();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        FFile fFile = request.getfFile();
        if (fFile == null) {
            response.setWrongInput(new NullFFileWrongInput());
            return response;
        }
        try {
            fileWriter.create(fFile);
        } catch (InOutException e) {
            response.setWrongInput(new FileExistsWrongInput());
        }
        return response;
    }

    @Override
    public String definition() {
        return "Create file with existing signature";
    }
}
