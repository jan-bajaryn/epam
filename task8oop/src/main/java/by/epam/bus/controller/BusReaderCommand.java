package by.epam.bus.controller;

import by.epam.bus.entity.Bus;
import by.epam.bus.factory.exception.IllegalBusInputException;
import by.epam.bus.factory.exception.IllegalPersonParamsException;
import by.epam.bus.parser.exception.IllegalInputCountException;
import by.epam.bus.parser.BusParser;
import by.epam.bus.dao.reader.BusReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BusReaderCommand {
    private BusReader busReader;
    private BusParser busParser;

    public BusReaderCommand() {
        busReader = new BusReader();
        busParser = new BusParser();
    }

    public List<Bus> execute(String fileName) {
        try {
            String[] strings = busReader.readBusesLines(fileName);
            return busParser.parseBusArray(strings);
        } catch (IOException e) {
            System.out.println("No such file found.");
        } catch (IllegalInputCountException e) {
            System.out.println("Error in line signature.");
        } catch (IllegalBusInputException e) {
            System.out.println("Wrong parameters for bus");
        } catch (IllegalPersonParamsException e) {
            System.out.println("Wrong parameters for person");
        }
        return new ArrayList<>();
    }
}
