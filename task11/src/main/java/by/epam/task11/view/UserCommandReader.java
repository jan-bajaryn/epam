package by.epam.task11.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Scanner;

public class UserCommandReader {
    private static final Logger log = LogManager.getLogger(UserCommandReader.class);

    private Scanner sc = new Scanner(System.in);


    public String printUserInterface(Map<String, String> commandsDefinitions) {

        log.info("commandDefinition.size = {}", commandsDefinitions.size());

        System.out.println("Please enter what to do.");
        for (Map.Entry<String, String> stringStringEntry : commandsDefinitions.entrySet()) {
            System.out.println(stringStringEntry.getValue() + " --> " + stringStringEntry.getKey());
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
