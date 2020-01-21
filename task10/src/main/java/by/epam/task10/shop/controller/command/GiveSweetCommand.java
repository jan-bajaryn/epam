package by.epam.task10.shop.controller.command;

import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.service.PurchasesService;
import by.epam.task10.shop.service.RemoveSweetService;
import by.epam.task10.shop.service.exception.IllegalSizeException;
import by.epam.task10.shop.service.exception.NoElementsToExchangeException;

public class GiveSweetCommand implements ExecCommand {
    private RemoveSweetService removeSweetService = new RemoveSweetService();
    private PurchasesService purchasesService = new PurchasesService();


    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (purchasesService.isEmptySweetsToAdd()) {
            response.setDisplayInformation("There nothing to remove.");
            return response;
        }
        Integer index = request.getIndex();
        if (index == null || index < 0) {
            response.setDisplayInformation("Index can't be less or more than existing or typed not like integer.");
            return response;
        }
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
