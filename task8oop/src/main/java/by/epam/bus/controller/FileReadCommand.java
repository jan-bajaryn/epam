package by.epam.bus.controller;

import by.epam.bus.entity.Bus;
import by.epam.bus.dao.reader.CommandsConsoleReader;
import by.epam.bus.dao.repos.BusStation;
import by.epam.bus.view.ConsolePrinter;

import java.util.List;

public class FileReadCommand {
    private ConsolePrinter consolePrinter;
    private CommandsConsoleReader commandsConsoleReader;
    private BusReaderCommand busReaderCommand;
    private ChooseOperations chooseOperations;

    public FileReadCommand() {
        consolePrinter = new ConsolePrinter();
        commandsConsoleReader = new CommandsConsoleReader();
        busReaderCommand = new BusReaderCommand();
        chooseOperations = new ChooseOperations();
    }

    public void execute() {
        String fileName = commandsConsoleReader.queryFileName();

        List<Bus> buses = busReaderCommand.execute(fileName);
        BusStation busStation = BusStation.getInstance();
        busStation.setBase(buses);
        if (!buses.isEmpty()) {
            chooseOperations.execute(busStation);
        }
    }
}
