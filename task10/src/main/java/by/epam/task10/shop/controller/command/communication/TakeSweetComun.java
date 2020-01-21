package by.epam.task10.shop.controller.command.communication;

import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.service.PurchasesService;
import by.epam.task10.shop.service.ShopService;
import by.epam.task10.shop.view.UserCommandReader;

public class TakeSweetComun implements CommunicationCommand {
    private UserCommandReader userCommandReader = new UserCommandReader();
    private ShopService shopService = new ShopService();
    private PurchasesService purchasesService = new PurchasesService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        if (purchasesService.isNullPackaging()) {
            request.setIndex(null);
            return response;
        }
        Integer index = userCommandReader.readIndexSweet(shopService.findAllSweets());
        request.setIndex(index);
        return response;
    }

}
