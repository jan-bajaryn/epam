package by.epam.task10.textfile.controller.command;

import by.epam.task10.textfile.dao.InOutException;
import by.epam.task10.textfile.entity.FFile;
import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;
import by.epam.task10.textfile.service.FileWriterService;

public class CreateFileCommand implements ExecCommand {
    private FileWriterService fileWriterService = new FileWriterService();
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
            fileWriterService.create(fFile);
        } catch (InOutException e) {
            response.setDisplayInformation("File already exists.");
        }
        return response;
    }

    @Override
    public String definition() {
        return "Create file with existing signature";
    }
}
