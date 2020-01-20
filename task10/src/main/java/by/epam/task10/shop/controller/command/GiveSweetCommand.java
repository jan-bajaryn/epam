package by.epam.task10.shop.controller.command;

import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.service.exception.IllegalSizeException;
import by.epam.task10.shop.service.exception.NoElementsToExchangeException;
import by.epam.task10.shop.service.RemoveSweetService;
import by.epam.task10.shop.view.communication.EmptySweetPurchasesWrongInput;
import by.epam.task10.shop.view.communication.IndexWrongInput;

public class GiveSweetCommand implements ExecCommand {
    private Purchases purchases = Purchases.getInstance();
    private RemoveSweetService removeSweetService = new RemoveSweetService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (purchases.isEmptySweetsToAdd()) {
            response.setWrongInput(new EmptySweetPurchasesWrongInput());
            return response;
        }
        Integer index = request.getIndex();
        if (index == null || index < 0) {
            response.setWrongInput(new IndexWrongInput());
            return response;
        }
//TODO realize this method too
        try {
            removeSweetService.remove(index);
        } catch (IllegalSizeException e) {
            response.setDisplayInformation("Please choose size from the list.");
        } catch (NoElementsToExchangeException e) {
            response.setDisplayInformation("There no elements this type in the gift");
        }
        return response;
    }

    @Override
    public String definition() {
        return "Remove sweet from your gift and add it to the shop.";
    }
}
