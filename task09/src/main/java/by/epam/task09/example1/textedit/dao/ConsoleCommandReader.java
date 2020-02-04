package by.epam.task09.example1.textedit.dao;

import java.util.Scanner;

public class ConsoleCommandReader {

    public static final String READING_FILE = "R";
    public static final String SELF_INPUT = "S";
    public static final String EXIT = "E";
    public static final String APPENT_TEXT = "A";
    public static final String PRINT_CONSOLE = "P";
    public static final String PRINT_CONSOLE_HEADER = "PH";
    public static final String WRITE_FILE = "W";

    private Scanner sc = new Scanner(System.in);

    public String readFirstCommand() {
        System.out.println("Please input what you want to do:");
        System.out.println(EXIT + " - exit");
        System.out.println(SELF_INPUT + " - self input text");
        System.out.println(READING_FILE + " - get text from file");
        return sc.nextLine();
    }

    public String readFileName() {
        System.out.println("Please enter fileName with extension.");
        return sc.nextLine();
    }

    public String selfInput() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Enter the header of your text");
        sb.append("Head: ").append("\n").append(sc.nextLine()).append("\n");
        System.out.println("Please enter sentences in full format. Don't forget to not use nextLine or wrong format of the text.");
        sb.append("Sentences: ").append("\n").append(sc.nextLine());
        return sb.toString();
    }

    public String readSecondCommand() {
        System.out.println("Choose command");
        System.out.println(APPENT_TEXT + " - append to file one sentence.");
        System.out.println(PRINT_CONSOLE + " - print to console your text");
        System.out.println(PRINT_CONSOLE_HEADER + " - print to console your header");
        System.out.println(WRITE_FILE + " - write result to file");
        System.out.println(EXIT + " - exit");
        return sc.nextLine();
    }

    public String readSentence() {
        System.out.println("Enter your sentence in appropriate format.");
        return sc.nextLine();
    }
}
