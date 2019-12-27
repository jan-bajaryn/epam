package by.epam.bus.controller;

import by.epam.bus.dao.reader.BusReader;
import by.epam.bus.entity.Bus;
import by.epam.bus.factory.exception.IllegalBusInputException;
import by.epam.bus.factory.exception.IllegalPersonParamsException;
import by.epam.bus.parser.BusParser;
import by.epam.bus.parser.exception.IllegalInputCountException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class BusReaderCommand {
    private BusReader busReader = new BusReader();
    private BusParser busParser = new BusParser();


    public List<Bus> execute(String fileName) {
        try {
            String[] strings = busReader.readBusesLines(fileName);
            return busParser.parseBusArray(strings);
        } catch (IOException e) {
            out.println("No such file found.");
        } catch (IllegalInputCountException e) {
            out.println("Error in line signature.");
        } catch (IllegalBusInputException e) {
            out.println("Wrong parameters for bus");
        } catch (IllegalPersonParamsException e) {
            out.println("Wrong parameters for person");
        }
        return new ArrayList<>();
    }
}
