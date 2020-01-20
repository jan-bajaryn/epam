package by.epam.task10.shop.controller.command.communication;

import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.controller.command.communication.CommunicationCommand;
import by.epam.task10.shop.view.UserCommandReader;

public class GiveSweetComun implements CommunicationCommand {
    private Purchases purchases = Purchases.getInstance();
    private UserCommandReader userCommandReader = new UserCommandReader();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (purchases.isEmptySweetsToAdd()) {
            request.setIndex(null);
            return response;
        }

        request.setIndex(userCommandReader.readIndexSweet(purchases.getToAdd().getSweets()));
        return response;
    }
}
