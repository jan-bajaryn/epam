package by.epam.task10.shop.controller.command;

import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.entity.Gift;
import by.epam.task10.shop.entity.dialog.Request;
import by.epam.task10.shop.entity.dialog.Response;
import by.epam.task10.shop.entity.factory.GiftFactory;
import by.epam.task10.shop.service.ChangeOrGetPackaging;
import by.epam.task10.shop.view.communication.IndexWrongInput;

public class ChangePackagingCommand implements ExecCommand {
    private Shop shop = Shop.getInstance();
    private GiftFactory giftFactory = new GiftFactory();
    private ChangeOrGetPackaging PackagingService = new ChangeOrGetPackaging();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        if (shop.findAllPackaging().isEmpty()) {
            response.setDisplayInformation("There nothing to choose. Exit if you collected your purchase.");
            return response;
        }
        Integer index = request.getIndex();
        if (index == null || index < 0) {
            response.setWrongInput(new IndexWrongInput());
        }
        Gift gift = request.getGift();
        if (gift == null) {
            gift = giftFactory.createEmpty();
        }
        PackagingService.changeOrGetPackagin(gift, index);
        request.setGift(gift);

        return response;
    }

    @Override
    public String definition() {
        return "Change or choose package.";
    }
}
