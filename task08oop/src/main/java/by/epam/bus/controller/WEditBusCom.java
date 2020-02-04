package by.epam.bus.controller;

import by.epam.bus.dao.reader.CommandsConsoleReader;
import by.epam.bus.entity.Bus;
import by.epam.bus.view.BusListPrinter;

import java.io.IOException;
import java.util.List;
import static java.lang.System.out;

public class WEditBusCom {
    private CommandsConsoleReader commandsConsoleReader = new CommandsConsoleReader();
    private BusListPrinter busListPrinter = new BusListPrinter();


    public void execute(List<Bus> buses) {
        String answer = commandsConsoleReader.writeEditable();
        if (CommandsConsoleReader.YES.equals(answer)) {
            String fileName = commandsConsoleReader.readFileNameToWrite();
            try {
                busListPrinter.printEditable(buses, fileName);
            } catch (IOException e) {
                out.println("Wrong name to file in input.");
            }
        }
    }
}
