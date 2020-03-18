package by.epam.task13.consolecontroller.controller;


import by.epam.task13.consolecontroller.controller.command.ChangeLanguageCommand;
import by.epam.task13.consolecontroller.controller.command.ExecCommand;
import by.epam.task13.consolecontroller.controller.command.ExitExecCommand;
import by.epam.task13.consolecontroller.controller.command.ParseXmlCommand;
import by.epam.task13.consolecontroller.controller.command.communication.CommunicationCommand;
import by.epam.task13.consolecontroller.controller.command.communication.ParseXmlComun;
import by.epam.task13.consolecontroller.controller.command.dialog.Request;
import by.epam.task13.consolecontroller.controller.command.dialog.Response;
import by.epam.task13.view.UserCommandReader;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    public static Locale locale = new Locale("RU", "ru");

    private UserCommandReader userCommandReader = new UserCommandReader();

    public void run() {

        Map<String, ExecCommand> commandMap = new HashMap<>();
        Map<String, CommunicationCommand> comunCommands = new HashMap<>();

        commandMap.put(Response.EXIT, new ExitExecCommand());

        commandMap.put("cL", new ChangeLanguageCommand());

        comunCommands.put("parse", new ParseXmlComun());
        commandMap.put("parse", new ParseXmlCommand());

        Map<String, String> commandsDefinitions = commandMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, c -> c.getValue().definition()));
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
