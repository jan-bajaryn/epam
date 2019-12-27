package by.epam.bus.dao.reader;

import by.epam.bus.view.ConsolePrinter;

import java.util.Scanner;

public class CommandsConsoleReader {

    public static final String READING_FILE = "R";
    public static final String EXIT = "E";
    public static final String BUSES_BY_TRACK = "T";
    public static final String MORE_EXPLOITATION = "ME";
    public static final String MORE_MILLAGE = "M";
    public static final String DRIVER = "D";
    public static final String GENERATE_COLLECTION = "G";
    public static final String FROM_FILE = "F";
    public static final String YES = "Y";

    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter consolePrinter = new ConsolePrinter();

    public String chooseCommandBegin() {
        consolePrinter.greet();
        return sc.nextLine();
    }

    public String queryFileName() {
        consolePrinter.fileNameQuary();
        return sc.nextLine();
    }


    public String chooseOperations() {
        consolePrinter.chooseOperationQuary();
        return sc.nextLine();
    }


    public String busesByTrack() {
        consolePrinter.trackNumberQuary();
        return sc.nextLine();
    }


    public String readFileNameToWrite() {
        consolePrinter.quaryFileNameWrite();
        return sc.nextLine();
    }

    public String moreExpluitation() {
        consolePrinter.quaryExpluitation();
        return sc.nextLine();
    }

    public String moreMillage() {
        consolePrinter.quaryMillage();
        return sc.nextLine();
    }

    public String readFileChoose() {
        consolePrinter.quaryFileRead();
        return sc.nextLine();
    }

    public String chooseSizeGenerate() {
        consolePrinter.quarySize();
        return sc.nextLine();
    }

    public String writeEditable() {
        consolePrinter.quaryWriteEditable();
        return sc.nextLine();
    }

}
