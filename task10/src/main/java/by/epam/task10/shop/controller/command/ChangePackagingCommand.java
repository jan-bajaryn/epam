package by.epam.task10.shop.controller.command;

import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.service.ChangeOrGetPackaging;
import by.epam.task10.shop.service.PurchasesService;
import by.epam.task10.shop.service.ShopService;
import by.epam.task10.shop.service.exception.InvalidIndexCountException;
import by.epam.task10.shop.service.exception.NoElementsToExchangeException;

public class ChangePackagingCommand implements ExecCommand {
    private ChangeOrGetPackaging packagingService = new ChangeOrGetPackaging();
    private ShopService shopService = new ShopService();
    private PurchasesService purchasesService = new PurchasesService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (!purchasesService.isEmptySweetsToAdd()) {
            response.setDisplayInformation("Your gift not empty. Delete all sweets and than change packaging.");
            return response;
        }

        if (shopService.isEmptyPackages()) {
            response.setDisplayInformation("There nothing to choose. Exit if you collected your purchase.");
            return response;
        }
        Integer index = request.getIndex();
        if (index == null || index < 0) {
            response.setDisplayInformation("Index can't be less or more than existing or typed not like integer.");
            return response;
        }
        try {
            packagingService.changeOrGetPackagin(index);
        } catch (InvalidIndexCountException e) {
            response.setDisplayInformation("Please choose index from the list.");
        } catch (NoElementsToExchangeException e) {
            response.setDisplayInformation("There no packaging in the shop this type to get(count = 0).");
        }

        return response;
    }

    @Override
    public String definition() {
        return "Change or choose package.";
    }
}
