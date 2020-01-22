package by.epam.task10.textfile.controller.command;

import by.epam.task10.textfile.entity.FFile;
import by.epam.task10.textfile.controller.command.dialog.Request;
import by.epam.task10.textfile.controller.command.dialog.Response;
import by.epam.task10.textfile.entity.TextFFile;
import by.epam.task10.textfile.entity.factory.FFileFactory;
import by.epam.task10.textfile.entity.factory.IllegalPathException;
import by.epam.task10.textfile.entity.factory.TextFileFactory;

import java.util.List;

public class ChangeSignatureCommand implements ExecCommand {
    FFileFactory fFileFactory = new FFileFactory();
    TextFileFactory textFileFactory = new TextFileFactory();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        List<Object> list = request.getData();
        try {
            if ((Boolean) list.get(0)) {
                TextFFile textFFile = textFileFactory.create((String) list.get(2), (String) list.get(1));
                request.setfFile(textFFile);
            } else {
                FFile fFile = fFileFactory.create((String) list.get(2), (String) list.get(1), (String) list.get(3));
                request.setfFile(fFile);
            }
            return response;
        } catch (ClassCastException | NullPointerException | IndexOutOfBoundsException e) {
            response.setDisplayInformation("Wrong parameters to file creation");
            return response;
        } catch (IllegalPathException e) {
            response.setDisplayInformation("Wrong path to file.");
            return response;
        }

    }

    @Override
    public String definition() {
        return "Change signature for file";
    }
}
