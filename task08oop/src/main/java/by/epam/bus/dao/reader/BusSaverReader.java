package by.epam.bus.dao.reader;

import java.util.Scanner;

public class BusSaverReader {
    public static final String EDITABLE = "E";
    public static final String READABLE = "R";
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        System.out.println("Do you want to write editable version, or readable?");
        System.out.println("Editable - " + EDITABLE);
        System.out.println("Readable - " + READABLE);
        return sc.nextLine();
    }
}
