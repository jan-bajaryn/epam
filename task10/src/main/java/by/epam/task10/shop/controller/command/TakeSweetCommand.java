package by.epam.task10.shop.controller.command;

import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.dao.Shop;
import by.epam.task10.shop.entity.Packaging;
import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.entity.factory.exception.IllegalFactParamSweetException;
import by.epam.task10.shop.service.*;
import by.epam.task10.shop.service.exception.IllegalIndexSweetException;
import by.epam.task10.shop.service.exception.NoSweetFoundException;
import by.epam.task10.shop.service.exception.NotContainsPackagingException;
import by.epam.task10.shop.service.exception.NotEnoughSizeException;

import java.util.Map;

public class TakeSweetCommand implements ExecCommand {
    private Shop shop = Shop.getInstance();
    private Purchases purchases = Purchases.getInstance();
    private TakeSweetService takeSweetService = new TakeSweetService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);


        if (purchases.getToAdd().getPackaging() == null) {
            response.setDisplayInformation("You must choose packaging before adding sweets.");
            return response;
        }

        Map<Packaging, Integer> allPackaging = shop.findAllPackaging();
        if (allPackaging == null || allPackaging.isEmpty()) {
            response.setDisplayInformation("There no any sweets in shop. You can please to bring more sweets.");
            return response;
        }

        Integer index = request.getIndex();
        if (index == null || index < 0) {
            response.setDisplayInformation("Index can't be less or more than existing or typed not like integer.");
            return response;
        }
        try {
            takeSweetService.takeSweet(index);
        } catch (IllegalIndexSweetException e) {
            response.setDisplayInformation("Please choose index from the list.");
        } catch (IllegalFactParamSweetException | NoSweetFoundException e) {
            response.setDisplayInformation("There not any sweet of that type in the shop(count=0)");
        } catch (NotContainsPackagingException e) {
            response.setDisplayInformation("You must choose packaging before adding sweets.");
        } catch (NotEnoughSizeException e) {
            response.setDisplayInformation("Not enough size for this sweet.");
        }

        return response;
    }

    @Override
    public String definition() {
        return "Take a sweet to your gift.";
    }
}
