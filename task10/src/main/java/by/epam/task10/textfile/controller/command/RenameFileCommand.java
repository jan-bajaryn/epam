package by.epam.task10.textfile.controller.command;

import by.epam.task10.textfile.dao.InOutException;
import by.epam.task10.textfile.entity.FFile;
import by.epam.task10.textfile.controller.command.dialog.Request;
import by.epam.task10.textfile.controller.command.dialog.Response;
import by.epam.task10.textfile.service.FileWriterService;

public class RenameFileCommand implements ExecCommand {
    private FileWriterService fileWriterService = new FileWriterService();
    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        FFile fFile = request.getfFile();
        String fileName = request.getStringData();

        if (fFile == null) {
            response.setDisplayInformation("You must make signature for this file at first. Choose 'change signature' for that");
            return response;
        }
        if (fileName == null) {
            response.setDisplayInformation("File name can't be null.");
            return response;
        }

        try {
            fileWriterService.rename(fFile, fileName);
        } catch (InOutException e) {
            response.setDisplayInformation("File already exists.");
        }
        return response;
    }

    @Override
    public String definition() {
        return "Rename file";
    }
}
