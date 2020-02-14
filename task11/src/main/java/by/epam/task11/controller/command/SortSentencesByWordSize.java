package by.epam.task11.controller.command;

import by.epam.task11.controller.command.dialog.Request;
import by.epam.task11.controller.command.dialog.Response;
import by.epam.task11.entities.CompType;
import by.epam.task11.service.sorting.CompositeSort;
import by.epam.task11.service.sorting.impl.SortByChildSize;

public class SortSentencesByWordSize implements by.epam.task11.controller.ExecCommand {

//    private SortSentencesByWordSizeService sortSentencesByWordSizeService = new SortSentencesByWordSizeService();
    private CompositeSort compositeSort = new SortByChildSize();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        if (request.getComposite() != null) {
//            sortSentencesByWordSizeService.sort(request.getComposite());
            compositeSort.sort(request.getComposite(), CompType.TOKEN);
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
