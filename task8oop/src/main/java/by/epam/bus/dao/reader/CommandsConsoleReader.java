package by.epam.bus.dao.reader;

import java.util.Scanner;

public class CommandsConsoleReader {

    public static final String READING_FILE = "R";
    public static final String EXIT = "E";
    public static final String BUSES_BY_TRACK = "T";
    public static final String MORE_EXPLOITATION = "ME";
    public static final String MORE_MILLAGE = "M";
    public static final String DRIVER = "D";

    private Scanner sc;

    public CommandsConsoleReader() {
        sc = new Scanner(System.in);
    }

    public String chooseCommandBegin() {
        System.out.println("Choose command: ");
        System.out.println("Reading from file: " + READING_FILE);
        System.out.println("Exit: " + EXIT);
        return sc.nextLine();
    }

    public String queryFileName() {
        System.out.println("Please write fileName, where we should take a base.");
        return sc.nextLine();
    }

    public String chooseOperations() {
        System.out.println("Choose operations on database.");
        System.out.println("Get Buses by track number: " + BUSES_BY_TRACK);
        System.out.println("Get Buses that have more exploitation years than input: " + MORE_EXPLOITATION);
        System.out.println("Get Buses that have more millage years than input: " + MORE_MILLAGE);
        System.out.println("Get Buses by driver with so name, surname and father name: " + DRIVER);
        System.out.println("Exit: " + EXIT);
        return sc.nextLine();
    }

    public String busesByTrack() {
        System.out.println("Write track number.");
        return sc.nextLine();
    }

    public String readFileName() {
        System.out.println("Write fileName.");
        return sc.nextLine();
    }

    public String moreExpluitation() {
        System.out.println("Write year number.");
        return sc.nextLine();
    }

    public String moreMillage() {
        System.out.println("Write millage count.");
        return sc.nextLine();
    }
}
