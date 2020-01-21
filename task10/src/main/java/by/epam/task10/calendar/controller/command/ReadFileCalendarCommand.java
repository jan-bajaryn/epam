package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.controller.command.dialog.Request;
import by.epam.task10.calendar.controller.command.dialog.Response;
import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.service.XMLFileReaderService;
import by.epam.task10.calendar.service.validator.CalendarValidator;

import java.io.FileNotFoundException;

public class ReadFileCalendarCommand implements ExecCommand {
    private CalendarValidator calendarValidator = new CalendarValidator();
    private XMLFileReaderService xmlFileReaderService = new XMLFileReaderService();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        String fileName = request.getFileName();
        if (fileName == null) {
            response.setDisplayInformation("File name can't be null.");
            return response;
        }
        try {
            Calendar calendar = xmlFileReaderService.read(fileName);
            if (calendarValidator.isValid(calendar)) {
                request.setCalendar(calendar);
            } else {
                response.setDisplayInformation("Format of data in this file is illegal. Please choose another file.");
            }
            return response;
        } catch (FileNotFoundException e) {
            response.setDisplayInformation("There not so file found. Please choose another file name.");
            return response;
        }

    }

    @Override
    public String definition() {
        return "Replace existing calendar with new from file.";
    }
}
