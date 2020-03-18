package by.epam.task13.view;

import by.epam.task13.consolecontroller.controller.Controller;
import by.epam.task13.consolecontroller.controller.command.ExecCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UserCommandReader {
    private static final Logger log = LogManager.getLogger(UserCommandReader.class);

    private Scanner sc = new Scanner(System.in);


    public String printUserInterface(Map<String, ExecCommand> commandsDefinitions) {

        log.info("commandDefinition.size = {}", commandsDefinitions.size());


        ResourceBundle rb = ResourceBundle.getBundle("property.text", Controller.locale);
        System.out.println(rb.getString("consoleinput.whattodo"));
        for (Map.Entry<String, ExecCommand> stringStringEntry : commandsDefinitions.entrySet()) {
            System.out.println(stringStringEntry.getValue().definition() + " --> " + stringStringEntry.getKey());
        }
        return sc.nextLine();
    }

    public void noCommand() {
        log.info("noCommand run");
        System.out.println("No command for this input.");
    }

    public void printDisplayInformation(String displayInformation) {
        log.info("printDisplayInformation run");
        System.out.println(displayInformation);
    }
}

