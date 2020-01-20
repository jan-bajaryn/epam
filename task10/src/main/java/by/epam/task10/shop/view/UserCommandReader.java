package by.epam.task10.shop.view;

import by.epam.task10.shop.entity.Packaging;
import by.epam.task10.shop.entity.Sweet;

import java.util.InputMismatchException;
import java.util.List;
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
        int counter = 0;
        for (Map.Entry<Packaging, Integer> entry : allPackaging.entrySet()) {
            Packaging key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(counter++ + ". " + key.getColor() + ", " + key.getSize() + "--> " + value);
        }
        Integer result = null;
        try {
            result = sc.nextInt();
        } catch (InputMismatchException e) {
        } finally {
            sc.nextLine();

        }
        return result;
    }

    public Integer readIndexSweet(List<Sweet> allSweets) {
        if (allSweets == null || allSweets.isEmpty()) {
            return null;
        }
        for (int i = 0; i < allSweets.size(); i++) {
            Sweet sweet = allSweets.get(i);
            System.out.println(i + ". " + "name: " + sweet.getName() +
                    ", size: " + sweet.getSize() + ", count in the shop -->" + sweet.getCount());
        }

        System.out.println("Please enter index of sweet what you want to add to your gift.");
        Integer result = null;
        try {
            result = sc.nextInt();
        } catch (InputMismatchException e) {
        } finally {
            sc.nextLine();
        }
        return result;
    }
}
