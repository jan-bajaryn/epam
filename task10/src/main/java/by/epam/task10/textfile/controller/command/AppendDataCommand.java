package by.epam.task10.textfile.controller.command;

import by.epam.task10.textfile.dao.InOutException;
import by.epam.task10.textfile.entity.FFile;
import by.epam.task10.textfile.controller.command.dialog.Request;
import by.epam.task10.textfile.controller.command.dialog.Response;
import by.epam.task10.textfile.service.FileWriterService;

public class AppendDataCommand implements ExecCommand {
    private FileWriterService fileWriterService = new FileWriterService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        String data = request.getStringData();
        FFile fFile = request.getfFile();
        if (fFile == null) {
            response.setDisplayInformation("You must make signature for this file at first. Choose 'change signature' for that");
            return response;
        }
        if (data == null) {
            response.setDisplayInformation("Input data can't be null");
            return response;
        }

        try {
            fileWriterService.append(data, fFile);
        } catch (InOutException e) {
            response.setDisplayInformation("File already not exists. Change signature to work further.");
        }

        return response;
    }

    @Override
    public String definition() {
        return "Append data to file with existing signature.";
    }
}
