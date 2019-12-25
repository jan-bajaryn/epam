package by.epam.bus.controller;

import by.epam.bus.dao.Bus;
import by.epam.bus.reader.CommandsConsoleReader;
import by.epam.bus.repos.BusStation;
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
        boolean isDone = false;

        while (!isDone) {

            String fileName = commandsConsoleReader.chooseCommandBegin();


            List<Bus> buses = busReaderCommand.execute();
            BusStation busStation = new BusStation(buses);
            if (buses.isEmpty()){
                isDone=true;
            } else {
                chooseOperations.execute(busStation);
            }


        }
    }
}
