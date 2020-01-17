package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.dao.XMLFileWriter;
import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.entity.FreeCelebrity;
import by.epam.task10.calendar.parser.FormatRespList;
import by.epam.task10.calendar.service.CalendarService;
import by.epam.task10.calendar.view.ReportWriter;
import by.epam.task10.calendar.view.communication.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class SerializeCommand implements ExecCommand {

    private XMLFileWriter xmlFileWriter = new XMLFileWriter();

    @Override
    public Response execute(Request request) {

        Response response = new Response();
        response.setNextRequest(request);

        Calendar calendar = request.getCalendar();

        if (calendar == null) {
            response.setWrongInput(new NullCalendarWrongImput());
            return response;
        }

        String fileName = request.getFileName();
        if (fileName == null) {
            response.setWrongInput(new NullFileNameWrongInput());
            return response;
        }


        try {
            xmlFileWriter.writeCalendarToXML(calendar, fileName);
            response.setDisplayInformation("File is ready.");
        } catch (FileNotFoundException e) {
            response.setWrongInput(new FileNotFoundWrongInput());
        }

        return response;
    }

    @Override
    public String definition() {
        return "Write your calendar to xml file.";
    }
}
