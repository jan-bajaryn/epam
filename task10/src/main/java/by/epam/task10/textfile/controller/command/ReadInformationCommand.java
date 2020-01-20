package by.epam.task10.textfile.controller.command;

import by.epam.task10.textfile.entity.FFile;
import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;
import by.epam.task10.textfile.service.FileReader;
import by.epam.task10.textfile.service.InOutException;

public class ReadInformationCommand implements ExecCommand {
    private FileReader fileReader = new FileReader();

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
            String textData = fileReader.getTextData(fFile);
            response.setDisplayInformation(textData);
        } catch (InOutException e) {
            response.setDisplayInformation("There no so file. Change signature to work with another file.");
        }
        return response;
    }

    @Override
    public String definition() {
        return "Read information from file with existing signature";
    }
}
