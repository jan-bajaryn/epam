package by.epam.task10.shop.controller.command.communication;

import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.service.PurchasesService;
import by.epam.task10.shop.view.UserCommandReader;

public class PrintParagonComun implements CommunicationCommand {
    private UserCommandReader userCommandReader = new UserCommandReader();
    private PurchasesService purchasesService = new PurchasesService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (purchasesService.isEmptyGiftList()) {
            request.setStringData(null);
            return response;
        }

        request.setStringData(userCommandReader.readFileName());
        return response;
    }
}
