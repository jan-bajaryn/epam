package by.epam.task11.view;

import java.util.Map;
import java.util.Scanner;

public class UserCommandReader {
    private Scanner sc = new Scanner(System.in);

    public String printUserInterface(Map<String, String> commandsDefinitions) {
        System.out.println("Please enter what to do.");
        for (Map.Entry<String, String> stringStringEntry : commandsDefinitions.entrySet()) {
            System.out.println(stringStringEntry.getValue() + " --> " + stringStringEntry.getKey());
        }
        return sc.nextLine();
    }

    public void noCommand() {
        System.out.println("No command for this input.");
    }

    public void printDisplayInformation(String displayInformation) {
        System.out.println(displayInformation);
    }
}
