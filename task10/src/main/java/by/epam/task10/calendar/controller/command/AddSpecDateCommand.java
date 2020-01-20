package by.epam.task10.calendar.controller.command;

import by.epam.task10.calendar.controller.ExecCommand;
import by.epam.task10.calendar.entity.factory.SpecDateFactory;
import by.epam.task10.calendar.entity.impl.SpecDate;
import by.epam.task10.calendar.controller.command.dialog.Request;
import by.epam.task10.calendar.controller.command.dialog.Response;

import java.time.LocalDate;
import java.util.List;

public class AddSpecDateCommand implements ExecCommand {

    private SpecDateFactory specDateFactory = new SpecDateFactory();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);
        List<Object> params = request.getParams();
        try {
            SpecDate specDate = specDateFactory.create((Boolean) params.get(0),
                    (Boolean) params.get(1),
                    (String) params.get(3),
                    (String) params.get(2),
                    LocalDate.of((Integer) params.get(4), (Integer) params.get(5), (Integer) params.get(6)));
            request.getCalendar().getManager().getSpecificDays().add(specDate);
        } catch (Exception e) {
            response.setDisplayInformation("Not right name, description or date.");
            return response;
        }
        response.setDisplayInformation("Added successfully.");
        return response;
    }

    @Override
    public String definition() {
        return "Add specific day to calendar";
    }
}
