package by.epam.task10.textfile.controller.command;

import by.epam.task10.textfile.entity.FFile;
import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;
import by.epam.task10.textfile.service.FileWriter;
import by.epam.task10.textfile.service.InOutException;

public class DeleteFileCommand implements ExecCommand {
    private FileWriter fileWriter = new FileWriter();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        FFile fFile = request.getfFile();
        if (fFile == null) {
            response.setDisplayInformation("You must make signature for this file at first. Choose 'change signature' for that");
            return response;
        }
        try {
            fileWriter.delete(fFile);
        } catch (InOutException e) {
            response.setDisplayInformation("There no so file. Change signature to work with another file.");
        }
        return response;
    }

    @Override
    public String definition() {
        return "Create file with existing signature";
    }
}
