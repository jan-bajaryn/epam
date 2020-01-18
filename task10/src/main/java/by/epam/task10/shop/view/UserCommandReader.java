package by.epam.task10.shop.view;

import by.epam.task10.shop.entity.Packaging;

import java.util.InputMismatchException;
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
        System.out.println("There no so command");
    }

    public void printDisplayInformation(String displayInformation) {
        System.out.println(displayInformation);
    }

    public Integer choosePackaging(Map<Packaging, Integer> allPackaging) {
        if (allPackaging.isEmpty()) {
            return null;
        }
        allPackaging.entrySet().stream()
                .forEach(e -> {
                    Packaging key = e.getKey();
                    System.out.println(key.getColor() + ", " + key.getSize() + "--> " + e.getValue());
                });
        Integer result = null;
        try {
            result = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
        }
        return result;
    }
}
