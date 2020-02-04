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
import by.epam.bus.view.ConsolePrinter;

import static java.lang.System.out;

import java.util.List;

public class ChooseOperations {
    private CommandsConsoleReader commandsConsoleReader = new CommandsConsoleReader();
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private TrackBusParser trackBusParser = new TrackBusParser();
    private PersonConsoleReader personConsoleReader = new PersonConsoleReader();
    private BusSaverCommand busSaverCommand = new BusSaverCommand();


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
                        busSaverCommand.write(buses, "Список автобусов по маршруту :" + track, "", null);
                    } catch (IllegalFormatIntegerException e) {
                        out.println("Illegal input integer.");
                    }
                    break;
                case CommandsConsoleReader.MORE_EXPLOITATION:
                    try {
                        String yearSt = commandsConsoleReader.moreExpluitation();
                        int year = trackBusParser.parseYear(yearSt);
                        List<Bus> buses = busStation.busesMoreExploitation(year);
                        busSaverCommand.write(buses, "Список автобусов привысивших  :" + year + " лет экспуотации",
                                "", null);
                    } catch (IllegalFormatIntegerException e) {
                        out.println("Illegal input integer.");
                    } catch (IllegalYearCountInputException e) {
                        out.println("You wrought wrong count of years.");
                    }
                    break;
                case CommandsConsoleReader.MORE_MILLAGE:
                    try {
                        String millageSt = commandsConsoleReader.moreMillage();
                        int millage = trackBusParser.parseMillage(millageSt);
                        List<Bus> buses = busStation.busesMoreMillage(millage);
                        busSaverCommand.write(buses, "Список автобусов пробег которых выше: " + millage,
                                "", null);
                    } catch (IllegalFormatIntegerException e) {
                        out.println("Illegal input integer.");
                    } catch (IllegalMillageInputException e) {
                        out.println("You wrote illegal millage.");
                    }
                    break;
                case CommandsConsoleReader.DRIVER:
                    try {
                        Person driver = personConsoleReader.readPerson();
                        List<Bus> buses = busStation.busesByDriver(driver);
                        busSaverCommand.write(buses, "Список автобусов с водителем: \n",
                                "", driver);

                    } catch (IllegalPersonParamsException e) {
                        out.println("Incorrect input for driver parameters.");
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
