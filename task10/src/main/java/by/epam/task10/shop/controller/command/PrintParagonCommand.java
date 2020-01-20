package by.epam.task10.shop.controller.command;

import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.dao.Purchases;
import by.epam.task10.shop.service.PrintParagonService;
import by.epam.task10.shop.service.exception.ProblematicWriteFileException;

public class PrintParagonCommand implements ExecCommand {
    private Purchases purchases = Purchases.getInstance();
    private PrintParagonService printParagonService = new PrintParagonService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (purchases.isEmptyGiftList()) {
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
            purchases.clear();
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
