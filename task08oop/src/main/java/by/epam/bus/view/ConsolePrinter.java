package by.epam.bus.view;

import static by.epam.bus.dao.reader.CommandsConsoleReader.*;
import static java.lang.System.out;

public class ConsolePrinter {

    public void errorMessage() {
        out.println("Input incorrect. Try another option.");
    }

    public void quaryFileNameWrite() {
        out.println("Write fileName.");
    }

    public void quaryExpluitation() {
        out.println("Write year number.");
    }

    public void quaryMillage() {
        out.println("Write millage count.");
    }

    public void quaryFileRead() {
        out.println("Choose variant:");
        out.println("You want to generate random buses - " + GENERATE_COLLECTION);
        out.println("You want to choose input file - " + FROM_FILE);
    }

    public void quarySize() {
        out.println("Choose size to generate.");
    }

    public void quaryWriteEditable() {
        out.println("Do you want to write editable version to file?");
        out.println("yes - " + YES);
        out.println("other - anything");
    }

    public void trackNumberQuary() {
        out.println("Write track number.");
    }

    public void chooseOperationQuary() {
        out.println("Choose operations on database.");
        out.println("Get Buses by track number: " + BUSES_BY_TRACK);
        out.println("Get Buses that have more exploitation years than input: " + MORE_EXPLOITATION);
        out.println("Get Buses that have more millage years than input: " + MORE_MILLAGE);
        out.println("Get Buses by driver with so name, surname and father name: " + DRIVER);
        out.println("Exit: " + EXIT);
    }

    public void fileNameQuary() {
        out.println("Please write fileName, where we should take a base.");
    }

    public void greet() {
        out.println("Choose command: ");
        out.println("Reading from file: " + READING_FILE);
        out.println("Exit: " + EXIT);
    }

}
