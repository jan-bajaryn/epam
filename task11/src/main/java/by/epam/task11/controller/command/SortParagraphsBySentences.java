package by.epam.task11.controller.command;

import by.epam.task11.controller.command.dialog.Request;
import by.epam.task11.controller.command.dialog.Response;
import by.epam.task11.entities.CompType;
import by.epam.task11.service.sorting.CompositeSort;
import by.epam.task11.service.sorting.impl.SortByChildSize;

public class SortParagraphsBySentences implements by.epam.task11.controller.ExecCommand {

//    private SortParagraphsBySentService sortParagraphsBySentService = new SortParagraphsBySentService();
    private CompositeSort compositeSort = new SortByChildSize();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (request.getComposite() != null) {
//            sortParagraphsBySentService.sort(request.getComposite());
            compositeSort.sort(request.getComposite(), CompType.PARAGRAPH);
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
