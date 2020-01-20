package by.epam.task10.calendar.controller;

import by.epam.task10.calendar.controller.command.*;
import by.epam.task10.calendar.controller.command.communication.*;
import by.epam.task10.calendar.controller.command.dialog.Request;
import by.epam.task10.calendar.controller.command.dialog.Response;
import by.epam.task10.calendar.entity.IrregularFreeCelebrity;
import by.epam.task10.calendar.entity.impl.CatholicEaster;
import by.epam.task10.calendar.view.reader.UserCommandReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    public static List<IrregularFreeCelebrity> irregularDates;

    {
        irregularDates = new ArrayList<>();
        irregularDates.add(new CatholicEaster());
    }

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

        comunCommands.put("createEmpty", new CreateEmptyCalendarCommandComun());
        commandMap.put("createEmpty", new CreateEmptyCalendarCommand());

        comunCommands.put("addSpec", new AddSpecDateComun());
        commandMap.put("addSpec", new AddSpecDateCommand());

        comunCommands.put("addReg", new AddRegularDateComun());
        commandMap.put("addReg", new AddRegularDateCommand());

        comunCommands.put("addIrregular", new AddIrregularDateComun());
        commandMap.put("addIrregular", new AddIrregularDateCommand());


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

                if (response.getStatus() != null && response.getStatus().equals(Response.EXIT)) {
                    isExit = true;
                }


                String displayInformation = response.getDisplayInformation();
                if (displayInformation != null) {
                    userCommandReader.printDisplayInformation(displayInformation);
                    request = response.getNextRequest();
                }
            }

        }
    }

}
