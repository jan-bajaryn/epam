package by.epam.task11.controller.command;

import by.epam.task11.controller.command.dialog.Request;
import by.epam.task11.controller.command.dialog.Response;
import by.epam.task11.entities.Composite;
import by.epam.task11.service.impl.FileInformationReader;

import java.io.IOException;

public class PutCommand implements by.epam.task11.controller.ExecCommand {

    private FileInformationReader fileInformationReader = new FileInformationReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        String fileName = request.getFileName();

        if (fileName == null || fileName.isEmpty()) {
            response.setDisplayInformation("Illegal file name.");
            return response;
        }

        try {
            String s = fileInformationReader.readFile(fileName);
            if (request.getAbstractHandler() != null) {
                Composite chain = request.getAbstractHandler().chain(s);
                request.setComposite(chain);
                response.setDisplayInformation("Successfully read.");
            } else {
                response.setDisplayInformation("Abstract handler null. Not possible operation.");
            }
        } catch (IOException e) {
            response.setDisplayInformation("Wrong file name.");
        }

        return response;
    }

    @Override
    public String definition() {
        return "Take text from existing file.";
    }
}
