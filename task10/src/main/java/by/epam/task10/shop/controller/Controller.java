package by.epam.task10.shop.controller;


import by.epam.task10.shop.controller.command.BringCommand;
import by.epam.task10.shop.controller.command.ChangePackagingCommand;
import by.epam.task10.shop.controller.command.ExecCommand;
import by.epam.task10.shop.controller.command.ExitExecCommand;
import by.epam.task10.shop.controller.command.communication.ChangePackagingComun;
import by.epam.task10.shop.controller.command.communication.CommunicationCommand;
import by.epam.task10.shop.entity.dialog.Request;
import by.epam.task10.shop.entity.dialog.Response;
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

//        comunCommands.put("takeSweet", new TakeSweetComun());
//        commandMap.put("bring", new TakeSweetCommand());
//
//        comunCommands.put("giveSweet", new GiveSweetComun());
//        commandMap.put("bring", new GiveSweetCommand());
//
//
//        comunCommands.put("ready", new ReadyComun());
//        commandMap.put("ready", new ReadyCommand());
//
//        comunCommands.put("printParagon", new PrintParagonComun());
//        commandMap.put("ready", new PrintParagonCommand());



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
