package by.epam.task10.shop.controller.command.communication;

import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.entity.dialog.Request;
import by.epam.task10.shop.entity.dialog.Response;
import by.epam.task10.shop.view.UserCommandReader;

public class ChangePackagingComun implements CommunicationCommand {
    private UserCommandReader userCommandReader = new UserCommandReader();
    private Shop shop = Shop.getInstance();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        Integer integer = userCommandReader.choosePackaging(shop.findAllPackaging());
        request.setIndex(integer);
        return response;
    }
}
