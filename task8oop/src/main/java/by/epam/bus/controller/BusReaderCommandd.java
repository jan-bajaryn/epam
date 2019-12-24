package by.epam.bus.controller;

import by.epam.bus.dao.Bus;
import by.epam.bus.factory.exception.IllegalBusInputException;
import by.epam.bus.factory.exception.IllegalPersonParamsException;
import by.epam.bus.parser.IllegalInputCountException;
import by.epam.bus.parser.BusParser;
import by.epam.bus.reader.BusReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BusReaderCommandd {
    private BusReader busReader;
    private BusParser busParser;

    public BusReaderCommandd() {
        busReader = new BusReader();
        busParser = new BusParser();
    }

    public List<Bus> execute() {
        try {
            String[] strings = busReader.readBusesLines("buses.txt");
            return busParser.parseBusArray(strings);
        } catch (IOException | IllegalInputCountException e) {
            e.printStackTrace();
        } catch (IllegalBusInputException e) {
            e.printStackTrace();
        } catch (IllegalPersonParamsException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
