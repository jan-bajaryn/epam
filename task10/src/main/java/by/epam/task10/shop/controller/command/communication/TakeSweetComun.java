package by.epam.task10.shop.controller.command.communication;

import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.entity.dialog.Request;
import by.epam.task10.shop.entity.dialog.Response;
import by.epam.task10.shop.view.UserCommandReader;

public class TakeSweetComun implements CommunicationCommand {
    private UserCommandReader userCommandReader = new UserCommandReader();
    private Shop shop = Shop.getInstance();
    private Purchases purchases = Purchases.getInstance();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        if (purchases.getToAdd().getPackaging() == null) {
            request.setIndex(null);
            return response;
        }
        Integer index = userCommandReader.readIndexSweet(shop.findAllSweets());
        request.setIndex(index);
        return response;
    }
}
