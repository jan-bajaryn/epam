package by.epam.bus.controller;

import by.epam.bus.dao.reader.CommandsConsoleReader;
import by.epam.bus.dao.reader.PersonConsoleReader;
import by.epam.bus.dao.repos.BusStation;
import by.epam.bus.dao.repos.exception.IllegalMillageInputException;
import by.epam.bus.dao.repos.exception.IllegalYearCountInputException;
import by.epam.bus.entity.Bus;
import by.epam.bus.entity.Person;
import by.epam.bus.factory.exception.IllegalPersonParamsException;
import by.epam.bus.parser.TrackBusParser;
import by.epam.bus.parser.exception.IllegalFormatIntegerException;
import by.epam.bus.view.BusListPrinter;
import by.epam.bus.view.ConsolePrinter;

import java.io.IOException;
import java.util.List;

public class ChooseOperations {
    private CommandsConsoleReader commandsConsoleReader;
    private ConsolePrinter consolePrinter;
    private BusListPrinter busListPrinter;
    private TrackBusParser trackBusParser;
    private PersonConsoleReader personConsoleReader;

    public ChooseOperations() {
        commandsConsoleReader = new CommandsConsoleReader();
        consolePrinter = new ConsolePrinter();
        busListPrinter = new BusListPrinter();
        trackBusParser = new TrackBusParser();
        personConsoleReader = new PersonConsoleReader();
    }

    public void execute(BusStation busStation) {
        boolean isDone = false;

        while (!isDone) {

            String choose = commandsConsoleReader.chooseOperations();
            switch (choose) {
                case CommandsConsoleReader.BUSES_BY_TRACK:
                    try {
                        String trackSt = commandsConsoleReader.busesByTrack();
                        int track = trackBusParser.parseTrackValue(trackSt);
                        List<Bus> buses = busStation.busesByTrackNumber(track);
                        String fileName = commandsConsoleReader.readFileName();
                        busListPrinter.printToFile(buses, fileName, "Список автобусов по маршруту :" + track, "");
                    } catch (IOException e) {
                        System.out.println("Illegal format for the file");
                    } catch (IllegalFormatIntegerException e) {
                        System.out.println("Illegal input integer.");
                    }
                    break;
                case CommandsConsoleReader.MORE_EXPLOITATION:
                    try {
                        String yearSt = commandsConsoleReader.moreExpluitation();
                        int year = trackBusParser.parseYear(yearSt);
                        List<Bus> buses = busStation.busesMoreExploitation(year);
                        String fileName = commandsConsoleReader.readFileName();
                        busListPrinter.printToFile(buses, fileName,
                                "Список автобусов привысивших  :" + year + " лет экспуотации", "");
                    } catch (IOException e) {
                        System.out.println("Illegal format for the file");
                    } catch (IllegalFormatIntegerException e) {
                        System.out.println("Illegal input integer.");
                    } catch (IllegalYearCountInputException e) {
                        System.out.println("You wrought wrong count of years.");
                    }
                    break;
                case CommandsConsoleReader.MORE_MILLAGE:
                    try {
                        String millageSt = commandsConsoleReader.moreMillage();
                        int millage = trackBusParser.parseMillage(millageSt);
                        List<Bus> buses = busStation.busesMoreMillage(millage);
                        String fileName = commandsConsoleReader.readFileName();
                        busListPrinter.printToFile(buses, fileName,
                                "Список автобусов пробег :" + millage, "");
                    } catch (IOException e) {
                        System.out.println("Illegal format for the file");
                    } catch (IllegalFormatIntegerException e) {
                        System.out.println("Illegal input integer.");
                    } catch (IllegalMillageInputException e) {
                        System.out.println("You wrote illegal millage.");
                    }
                    break;
                case CommandsConsoleReader.DRIVER:
                    try {
                        Person driver = personConsoleReader.readPerson();

                        List<Bus> buses = busStation.busesByDriver(driver);
                        String fileName = commandsConsoleReader.readFileName();
                        busListPrinter.printToFile(buses, fileName, driver,
                                "Список автобусов с водителем: \n", "");
                    } catch (IOException e) {
                        System.out.println("Illegal format for the file");
                    } catch (IllegalPersonParamsException e) {
                        System.out.println("Incorrect input for driver parameters.");
                    }
                    break;
                case CommandsConsoleReader.EXIT:
                    isDone = true;
                    break;
                default:
                    consolePrinter.errorMessage();
            }
        }
    }
}
