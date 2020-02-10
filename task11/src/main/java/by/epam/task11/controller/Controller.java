package by.epam.task11.controller;


import by.epam.task11.controller.command.*;
import by.epam.task11.controller.command.communication.CommunicationCommand;
import by.epam.task11.controller.command.communication.PutComunCommand;
import by.epam.task11.controller.command.dialog.Request;
import by.epam.task11.controller.command.dialog.Response;
import by.epam.task11.service.impl.chain.impl.ParagraphHandler;
import by.epam.task11.service.impl.chain.impl.SentenceHandler;
import by.epam.task11.service.impl.chain.impl.TokenHandler;
import by.epam.task11.view.UserCommandReader;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {


    private UserCommandReader userCommandReader = new UserCommandReader();

    public void run() {

        Map<String, ExecCommand> commandMap = new HashMap<>();
        Map<String, CommunicationCommand> comunCommands = new HashMap<>();

        commandMap.put(Response.EXIT, new ExitExecCommand());

        commandMap.put("print", new PrintCommand());

        comunCommands.put("put", new PutComunCommand());
        commandMap.put("put", new PutCommand());


        commandMap.put("sort_par_s_c", new SortParagraphsBySentences());
        commandMap.put("sort_sentences_by_word_size", new SortSentencesByWordSize());

        Map<String, String> commandsDefinitions = commandMap.entrySet().stream()
                .collect(Collectors.toMap(c -> c.getKey(), c -> c.getValue().definition()));
        Response response = new Response();
        Request request = new Request();

        ParagraphHandler abstractHandler = new ParagraphHandler();
        SentenceHandler sentenceHandler = new SentenceHandler();
        TokenHandler tokenHandler = new TokenHandler();
        abstractHandler.setNextHandler(sentenceHandler);
        sentenceHandler.setNextHandler(tokenHandler);

        request.setAbstractHandler(abstractHandler);


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
