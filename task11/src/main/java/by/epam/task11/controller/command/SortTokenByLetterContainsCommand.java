package by.epam.task11.controller.command;

import by.epam.task11.controller.command.dialog.Request;
import by.epam.task11.controller.command.dialog.Response;
import by.epam.task11.entities.Composite;
import by.epam.task11.service.sorting.impl.SortTokenByLetterContains;

public class SortTokenByLetterContainsCommand implements by.epam.task11.controller.ExecCommand {

    private SortTokenByLetterContains sortTokenByLetterContains = new SortTokenByLetterContains();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        Character letter = request.getLetter();
        Composite composite = request.getComposite();

        if (check(response, letter, composite)) {
            return response;
        }

        sortTokenByLetterContains.sort(composite, letter);
        response.setDisplayInformation("Successfully sorted.");

        return response;
    }

    private boolean check(Response response, Character letter, Composite composite) {
        if (composite == null) {
            response.setDisplayInformation("You must at first read the composite.");
            return true;
        }
        if (letter == null) {
            response.setDisplayInformation("You must enter one letter.");
            return true;
        }
        return false;
    }

    @Override
    public String definition() {
        return "Sort tokens by count specific letter";
    }
}
