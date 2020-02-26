package by.epam.task12.controller;


import by.epam.task12.controller.command.*;
import by.epam.task12.controller.command.communication.CommunicationCommand;
import by.epam.task12.controller.command.communication.FillDiagonalComun;
import by.epam.task12.controller.command.dialog.Request;
import by.epam.task12.controller.command.dialog.Response;
import by.epam.task12.view.UserCommandReader;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {


    private UserCommandReader userCommandReader = new UserCommandReader();

    public void run() {

        Map<String, ExecCommand> commandMap = new HashMap<>();
        Map<String, CommunicationCommand> comunCommands = new HashMap<>();

        commandMap.put(Response.EXIT, new ExitExecCommand());

        comunCommands.put("fill_semaphore", new FillDiagonalComun());
        commandMap.put("fill_semaphore", new FillSemaphoreCommand());

        comunCommands.put("fill_lock", new FillDiagonalComun());
        commandMap.put("fill_lock", new FillLockCommand());

        comunCommands.put("fill_parts", new FillDiagonalComun());
        commandMap.put("fill_parts", new FillPartsCommand());

        comunCommands.put("fill_countDown", new FillDiagonalComun());
        commandMap.put("fill_countDown", new CountDownCommand());

        comunCommands.put("fill_atomic", new FillDiagonalComun());
        commandMap.put("fill_atomic", new FillAtomicCommand());

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
