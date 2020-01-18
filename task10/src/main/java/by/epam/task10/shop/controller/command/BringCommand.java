package by.epam.task10.shop.controller.command;

import by.epam.task10.shop.entity.dialog.Request;
import by.epam.task10.shop.entity.dialog.Response;
import by.epam.task10.shop.service.ItemsBringer;

import java.io.IOException;

public class BringCommand implements ExecCommand {
    public static final String FILE_NAME = "items.txt";

    ItemsBringer itemsBringer = new ItemsBringer();


    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        try {
            itemsBringer.bring(FILE_NAME);
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
