package by.epam.task10.calendar.view;

import java.util.Scanner;

public class InputDataReader {
    private Scanner sc = new Scanner(System.in);

    public String readFileName() {
        System.out.println("Please enter fileName with extension from what you want to take the calendar.");
        return sc.nextLine();
    }
}
