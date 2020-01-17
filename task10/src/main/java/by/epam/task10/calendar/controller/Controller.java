package by.epam.task10.calendar.controller;

import by.epam.task10.calendar.controller.command.*;
import by.epam.task10.calendar.controller.command.communication.*;
import by.epam.task10.calendar.controller.command.ShowThisMonth;
import by.epam.task10.calendar.view.communication.Request;
import by.epam.task10.calendar.view.communication.Response;
import by.epam.task10.calendar.view.reader.UserCommandReader;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private UserCommandReader userCommandReader = new UserCommandReader();

    public void run() {

        Map<String, ExecCommand> commandMap = new HashMap<>();
        Map<String, CommunicationCommand> comunCommands = new HashMap<>();

        commandMap.put(Response.EXIT, new ExitExecCommand());

        comunCommands.put("addAll", new AddAllCalendarFileComun());
        commandMap.put("addAll", new AddAllCalendarFile());

        comunCommands.put("replaceWith", new ReadFileCalendarCommandCommun());
        commandMap.put("replaceWith", new ReadFileCalendarCommand());

        commandMap.put("showYear", new ShowThisYearCommand());

        commandMap.put("showMonth", new ShowThisMonth());

        commandMap.put("showWeek", new ShowThisWeek());

        comunCommands.put("writeYearReport", new PrintReportToFileCommandComun());
        commandMap.put("writeYearReport", new PrintYearReportToFileCommand());

        comunCommands.put("writeXML", new SerializeCommandComun());
        commandMap.put("writeXML", new SerializeCommand());


        Map<String, String> commandsDefinitions = commandMap.entrySet().stream()
                .collect(Collectors.toMap(c -> c.getKey(), c -> c.getValue().definition()));
        Response response = new Response();
        Request request = new Request();


        boolean isExit = false;
        while (!isExit) {
            String act = userCommandReader.printUserInterface(commandsDefinitions);
            ExecCommand execCommand = commandMap.get(act);
            CommunicationCommand comCom = comunCommands.get(act);
            if (execCommand == null) {
                userCommandReader.noCommand();
            } else {

                if (comCom != null) {
                    response = comCom.execute(request);
                    request = response.getNextRequest();
                }

                response = execCommand.execute(request);

                if (response.getStatus() != null) {
                    if (response.getStatus().equals(Response.EXIT)) {
                        isExit = true;
                    }
                }


                if (response.getWrongInput() != null) {
                    response.getWrongInput().displayMessage();
                } else {
                    String displayInformation = response.getDisplayInformation();
                    if (displayInformation != null) {
                        userCommandReader.printDisplayInformation(displayInformation);
                        request = response.getNextRequest();
                    }
                }
            }

        }
    }

}
