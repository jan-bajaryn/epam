package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.controller.command.dialog.Request;
import by.epam.task10.calendar.controller.command.dialog.Response;
import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.entity.FreeCelebrity;
import by.epam.task10.calendar.parser.FormatRespList;
import by.epam.task10.calendar.service.CalendarService;
import by.epam.task10.calendar.view.ReportWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PrintYearReportToFileCommand implements ExecCommand {

    private ReportWriter reportWriter = new ReportWriter();
    private CalendarService calendarService = new CalendarService();
    private FormatRespList formatRespList = new FormatRespList();


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


        LocalDate now = LocalDate.now();
        Map<LocalDate, List<FreeCelebrity>> localDateListMap = calendarService.calcDays(calendar, LocalDate.of(now.getYear(), 1, 1),
                LocalDate.of(now.getYear() + 1, 1, 1));
        String parse = formatRespList.parse(localDateListMap);
        response.setDisplayInformation("Report is ready.");

        try {
            reportWriter.write(fileName, parse);
        } catch (IOException e) {
            response.setDisplayInformation("There not so file found. Please choose another file name.");
        }

        return response;
    }

    @Override
    public String definition() {
        return "Print report to text file.";
    }
}
