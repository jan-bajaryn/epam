package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.controller.command.dialog.Request;
import by.epam.task10.calendar.controller.command.dialog.Response;
import by.epam.task10.calendar.dao.XMLFileWriter;
import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.service.XMLFileWriterService;

import java.io.FileNotFoundException;

public class SerializeCommand implements ExecCommand {

    private XMLFileWriterService xmlFileWriterService = new XMLFileWriterService();

    @Override
    public Response execute(Request request) {

        Response response = new Response();
        response.setNextRequest(request);

        Calendar calendar = request.getCalendar();

        if (calendar == null) {
            response.setDisplayInformation("For this command you need to create or read calendar at first.");
            return response;
        }

        String fileName = request.getFileName();
        if (fileName == null) {
            response.setDisplayInformation("File name can't be null.");
            return response;
        }


        try {
            xmlFileWriterService.write(calendar, fileName);
            response.setDisplayInformation("File is ready.");
        } catch (FileNotFoundException e) {
            response.setDisplayInformation("There not so file found. Please choose another file name.");
        }

        return response;
    }

    @Override
    public String definition() {
        return "Write your calendar to xml file.";
    }
}
