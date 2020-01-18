package by.epam.task10.textfile.controller;


import by.epam.task10.textfile.controller.command.*;
import by.epam.task10.textfile.controller.command.communication.AppendFileComun;
import by.epam.task10.textfile.controller.command.communication.CommunicationCommand;
import by.epam.task10.textfile.controller.command.communication.ChangeSignatureComun;
import by.epam.task10.textfile.controller.command.communication.RenameFileComun;
import by.epam.task10.textfile.entity.Request;
import by.epam.task10.textfile.entity.Response;
import by.epam.task10.textfile.view.UserCommandReader;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    private UserCommandReader userCommandReader = new UserCommandReader();

    public void run() {

        Map<String, ExecCommand> commandMap = new HashMap<>();
        Map<String, CommunicationCommand> comunCommands = new HashMap<>();

        commandMap.put(Response.EXIT, new ExitExecCommand());

        comunCommands.put("sign", new ChangeSignatureComun());
        commandMap.put("sign", new ChangeSignatureCommand());

        commandMap.put("create", new CreateFileCommand());

        commandMap.put("delete", new DeleteFileCommand());

        commandMap.put("read", new ReadInformationCommand());

        comunCommands.put("append", new AppendFileComun());
        commandMap.put("append", new AppendDataCommand());

        comunCommands.put("rename", new RenameFileComun());
        commandMap.put("rename", new RenameFileCommand());


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
