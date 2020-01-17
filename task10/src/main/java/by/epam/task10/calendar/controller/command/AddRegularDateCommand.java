package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.entity.factory.exception.IllegalParamsSpecDateException;
import by.epam.task10.calendar.entity.factory.exception.IllegalRegularDayParamsException;
import by.epam.task10.calendar.entity.impl.RegularFreeCelebrity;
import by.epam.task10.calendar.view.communication.NullCalendarWrongImput;
import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;
import by.epam.task10.calendar.view.communication.WrongINputRegularParams;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class AddRegularDateCommand implements ExecCommand {
    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        List<Object> params = request.getParams();

        if (request.getCalendar() == null) {
            response.setWrongInput(new NullCalendarWrongImput());
        }

        try {
            RegularFreeCelebrity build = RegularFreeCelebrity.builder().celebrity((Boolean) params.get(0))
                    .free((Boolean) params.get(1))
                    .name((String) params.get(2))
                    .description((String) params.get(3))
                    .beginDate(LocalDate.of((Integer) params.get(4), (Integer) params.get(5), (Integer) params.get(6)))
                    .delta(Period.of((Integer) params.get(9), (Integer) params.get(8), (Integer) params.get(7)))
                    .direction(((Boolean) params.get(10))
                            ? RegularFreeCelebrity.Direction.FORWARD
                            : RegularFreeCelebrity.Direction.TWO_WAYS)
                    .build();
            request.getCalendar().getManager().getRegularDays().add(build);
        } catch (Exception e) {
            response.setWrongInput(new WrongINputRegularParams());
        }
        return response;
    }

    @Override
    public String definition() {
        return "Add regular date to calendar";
    }
}
