package by.epam.task11.view;


import java.util.Scanner;

public class InputDataReader {
    private Scanner sc = new Scanner(System.in);

    public String readFileName() {
        System.out.println("Please enter fileName with extension from what you want to take the calendar.");
        return sc.nextLine();
    }

    public Character readLetter() {
        System.out.println("Please enter letter using what you want to sort the text");
        String s = sc.nextLine();
        if (s.length() != 1) {
            return null;
        }
        return s.charAt(0);
    }
}
