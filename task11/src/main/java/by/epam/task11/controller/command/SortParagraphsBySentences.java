package by.epam.task11.controller.command;

import by.epam.task11.controller.command.dialog.Request;
import by.epam.task11.controller.command.dialog.Response;
import by.epam.task11.service.SortParagraphsBySentService;

public class SortParagraphsBySentences implements by.epam.task11.controller.ExecCommand {

    private SortParagraphsBySentService sortParagraphsBySentService = new SortParagraphsBySentService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (request.getComposite() != null) {
            sortParagraphsBySentService.sort(request.getComposite());
            response.setDisplayInformation("Successfully sorted.");
        } else {
            response.setDisplayInformation("composite is null. Can't do that operation.");
        }
        return response;
    }

    @Override
    public String definition() {
        return "Sort paragraphs by sentences count.";
    }
}
