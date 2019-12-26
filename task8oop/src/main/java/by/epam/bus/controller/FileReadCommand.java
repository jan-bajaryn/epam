package by.epam.bus.controller;

import by.epam.bus.entity.Bus;
import by.epam.bus.dao.reader.CommandsConsoleReader;
import by.epam.bus.dao.repos.BusStation;
import by.epam.bus.factory.exception.IllegalBusInputException;
import by.epam.bus.factory.exception.IllegalPersonParamsException;
import by.epam.bus.parser.SizeParser;
import by.epam.bus.parser.exception.IllegalFormatIntegerException;
import by.epam.bus.service.RandomBusGenerator;
import by.epam.bus.view.ConsolePrinter;

import java.io.IOException;
import java.util.List;

public class FileReadCommand {
    private CommandsConsoleReader commandsConsoleReader;
    private BusReaderCommand busReaderCommand;
    private ChooseOperations chooseOperations;

    public FileReadCommand() {
        commandsConsoleReader = new CommandsConsoleReader();
        busReaderCommand = new BusReaderCommand();
        chooseOperations = new ChooseOperations();
    }

    public void execute() {
        String choose = commandsConsoleReader.readFileChoose();
        switch (choose) {
            case CommandsConsoleReader.GENERATE_COLLECTION:
                try {
                    RandomBusGenerator randomBusGenerator = new RandomBusGenerator();
                    String sizeSt = commandsConsoleReader.chooseSizeGenerate();
                    SizeParser sizeParser = new SizeParser();
                    int size = sizeParser.parseSize(sizeSt);
                    BusStation busStation = BusStation.getInstance();
                    List<Bus> buses = randomBusGenerator.generate(size);
                    busStation.setBase(buses);
                    if (!buses.isEmpty()) {

                        WEditBusCom wEditBusCom = new WEditBusCom();
                        wEditBusCom.execute(buses);

                        chooseOperations.execute(busStation);
                    }
                } catch (IllegalFormatIntegerException e) {
                    System.out.println("Format of size in the input is incorrect.");
                } catch (IOException e) {
                    System.out.println("Problems in reading files.");
                } catch (IllegalBusInputException e) {
                    System.out.println("Illegal arguments in bus files.");
                } catch (IllegalPersonParamsException e) {
                    System.out.println("Illegal arguments in person files.");
                }
                break;
            case CommandsConsoleReader.FROM_FILE:
                String fileName = commandsConsoleReader.queryFileName();
                List<Bus> buses = busReaderCommand.execute(fileName);
                BusStation busStation = BusStation.getInstance();
                busStation.setBase(buses);
                if (!buses.isEmpty()) {
                    chooseOperations.execute(busStation);
                }
                break;
            default:
        }


    }
}
