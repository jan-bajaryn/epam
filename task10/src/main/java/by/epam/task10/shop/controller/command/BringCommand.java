package by.epam.task10.shop.controller.command;

import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.service.FilesItemsBringer;

import java.io.IOException;

public class BringCommand implements ExecCommand {
    public static final String FILE_NAME = "items.txt";

    FilesItemsBringer filesItemsBringer = new FilesItemsBringer();


    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        try {
            filesItemsBringer.bring(FILE_NAME);
        } catch (IOException e) {
            response.setDisplayInformation("There not items in factory. You can exit.");
        }
        return response;
    }

    @Override
    public String definition() {
        return "Make shop take more items from factories.";
    }
}
