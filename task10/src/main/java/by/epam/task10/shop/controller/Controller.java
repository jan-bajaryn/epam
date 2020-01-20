package by.epam.task10.shop.controller;


import by.epam.task10.shop.controller.command.*;
import by.epam.task10.shop.controller.command.communication.*;
import by.epam.task10.shop.controller.command.dialog.Request;
import by.epam.task10.shop.controller.command.dialog.Response;
import by.epam.task10.shop.view.UserCommandReader;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    private UserCommandReader userCommandReader = new UserCommandReader();

    public void run() {

        Map<String, ExecCommand> commandMap = new HashMap<>();
        Map<String, CommunicationCommand> comunCommands = new HashMap<>();

        commandMap.put(Response.EXIT, new ExitExecCommand());

        commandMap.put("bring", new BringCommand());

        comunCommands.put("changePackage", new ChangePackagingComun());
        commandMap.put("changePackage", new ChangePackagingCommand());

        comunCommands.put("takeSweet", new TakeSweetComun());
        commandMap.put("takeSweet", new TakeSweetCommand());
//
        comunCommands.put("giveSweet", new GiveSweetComun());
        commandMap.put("giveSweet", new GiveSweetCommand());
//
//
        commandMap.put("ready", new TakeGiftCommand());
//
        comunCommands.put("printParagon", new PrintParagonComun());
        commandMap.put("printParagon", new PrintParagonCommand());

        commandMap.put("printState", new PrintStateCommand());


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
