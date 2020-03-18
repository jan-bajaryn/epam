package by.epam.task13.consolecontroller.controller.command;

import by.epam.task13.consolecontroller.controller.Controller;
import by.epam.task13.consolecontroller.controller.command.dialog.Request;
import by.epam.task13.consolecontroller.controller.command.dialog.Response;
import by.epam.task13.service.LocaleChanger;

import java.util.ResourceBundle;

public class ChangeLanguageCommand implements ExecCommand {
    private LocaleChanger localeChanger = new LocaleChanger();

    @Override
    public Response execute(Request request) {
        Response response = new Response();
        response.setNextRequest(request);

        Controller.locale = localeChanger.changeLocale(Controller.locale);

        return response;
    }

    @Override
    public String definition() {
        ResourceBundle rb = ResourceBundle.getBundle("property.text", Controller.locale);
        return rb.getString("consoleinput.changelanguage");
    }
}
