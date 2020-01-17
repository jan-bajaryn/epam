package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.entity.Calendar;
import by.epam.task10.calendar.entity.FreeCelebrity;
import by.epam.task10.calendar.parser.FormatRespList;
import by.epam.task10.calendar.service.CalendarService;
import by.epam.task10.calendar.service.formatter.CalendarArgsFormatter;
import by.epam.task10.calendar.service.formatter.StringFormatter;
import by.epam.task10.calendar.view.communication.NullCalendarWrongImput;
import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

public class ShowThisYearCommand implements ExecCommand {

    CalendarService calendarService = new CalendarService();
    private FormatRespList formatRespList = new FormatRespList();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        Calendar calendar = request.getCalendar();

        if (calendar == null) {
            response.setWrongInput(new NullCalendarWrongImput());
            return response;
        }
        LocalDate now = LocalDate.now();
        Map<LocalDate, List<FreeCelebrity>> localDateListMap = calendarService.calcDays(calendar, LocalDate.of(now.getYear(), 1, 1),
                LocalDate.of(now.getYear() + 1, 1, 1));
        String parse = formatRespList.parse(localDateListMap);
        response.setDisplayInformation(parse);
        return response;


    }

    @Override
    public String definition() {
        return "Show this year";
    }
}
