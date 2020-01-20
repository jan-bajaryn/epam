package by.epam.task10.shop.controller.command;

import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.service.TakeGiftService;
import by.epam.task10.shop.service.exception.EmptySweetsException;
import by.epam.task10.shop.service.exception.NullPackagingException;

public class TakeGiftCommand implements ExecCommand {

    private TakeGiftService takeGiftService = new TakeGiftService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        try {
            takeGiftService.take();
        } catch (NullPackagingException e) {
            response.setDisplayInformation("You didn't take packaging.");
        } catch (EmptySweetsException e) {
            response.setDisplayInformation("You didn't take any sweets.");
        }
        return response;
    }

    @Override
    public String definition() {
        return "Make gift from sweets and packaging what you already took.";
    }
}
