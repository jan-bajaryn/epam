package by.epam.bus.controller;

import by.epam.bus.dao.reader.BusSaverReader;
import by.epam.bus.dao.reader.CommandsConsoleReader;
import by.epam.bus.entity.Bus;
import by.epam.bus.entity.Person;
import by.epam.bus.view.BusListPrinter;

import java.io.IOException;
import java.util.List;

public class BusSaverCommand {

    private BusSaverReader busSaverReader = new BusSaverReader();
    private BusListPrinter busListPrinter = new BusListPrinter();
    private CommandsConsoleReader commandsConsoleReader = new CommandsConsoleReader();


    public void write(List<Bus> list, String prefix, String postfix, Person person) {
        boolean isDone = false;
        while (!isDone) {
            switch (busSaverReader.readCommand()) {
                case BusSaverReader.EDITABLE:
                    try {
                        String fileName = commandsConsoleReader.readFileName();
                        busListPrinter.printEditable(list, fileName);
                        isDone = true;
                    } catch (IOException e) {
                        System.out.println("File name is incorrect.");
                    }
                    break;
                case BusSaverReader.READABLE:
                    try {
                        String fileName = commandsConsoleReader.readFileName();
                        if (person != null) {
                            busListPrinter.printToFile(list, fileName, person, prefix, postfix);
                        } else {
                            busListPrinter.printToFile(list, fileName, prefix, postfix);
                        }
                        isDone = true;
                    } catch (IOException e) {
                        System.out.println("fileName is incorrect.");
                    }
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
