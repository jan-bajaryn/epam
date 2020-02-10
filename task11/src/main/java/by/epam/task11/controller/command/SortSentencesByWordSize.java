package by.epam.task11.controller.command;

import by.epam.task11.controller.command.dialog.Request;
import by.epam.task11.controller.command.dialog.Response;
import by.epam.task11.service.impl.sorting.impl.SortSentencesByWordSizeService;

public class SortSentencesByWordSize implements by.epam.task11.controller.ExecCommand {

    private SortSentencesByWordSizeService sortSentencesByWordSizeService = new SortSentencesByWordSizeService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (request.getComposite() != null) {
            sortSentencesByWordSizeService.sort(request.getComposite());
            response.setDisplayInformation("Successfully sorted.");
        } else {
            response.setDisplayInformation("composite is null. Can't do that operation.");
        }
        return response;
    }

    @Override
    public String definition() {
        return "Sort sentences by word size";
    }
}
