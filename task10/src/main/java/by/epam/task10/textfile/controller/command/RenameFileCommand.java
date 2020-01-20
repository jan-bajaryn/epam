package by.epam.task10.textfile.controller.command;

import by.epam.task10.textfile.entity.FFile;
import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;
import by.epam.task10.textfile.service.FileWriter;
import by.epam.task10.textfile.service.InOutException;

public class RenameFileCommand implements ExecCommand {
    private FileWriter fileWriter = new FileWriter();

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
            fileWriter.rename(fFile, fileName);
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
