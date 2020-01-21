package by.epam.task10.shop.controller.command;

import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.service.PrintParagonService;
import by.epam.task10.shop.service.PurchasesService;
import by.epam.task10.shop.service.exception.ProblematicWriteFileException;

public class PrintParagonCommand implements ExecCommand {
    private PrintParagonService printParagonService = new PrintParagonService();
    private PurchasesService purchasesService = new PurchasesService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (purchasesService.isEmptyGiftList()) {
            response.setDisplayInformation("Nothing to print. Please, create any gift to make that.");
            return response;
        }
        String fileName = request.getStringData();
        if (fileName == null) {
            response.setDisplayInformation("Wrong file name. Please choose another");
            return response;
        }
        try {
            printParagonService.print(fileName);
            purchasesService.clear();
        } catch (ProblematicWriteFileException e) {
            response.setDisplayInformation("Problems in writing to file. Please choose another file name.");
        }
        return response;
    }

    @Override
    public String definition() {
        return "Print paragon to file.";
    }
}
