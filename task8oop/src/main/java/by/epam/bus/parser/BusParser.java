package by.epam.bus.parser;

import by.epam.bus.dao.Bus;
import by.epam.bus.exception.IllegalInputException;

import java.util.ArrayList;
import java.util.List;

public class BusParser {

    public static final String SPLITER = "---";
    public static final int ATTRIBUTE_COUNT = 6;
    public static final int SURNAME_NUMBER = 0;
    public static final int BUS_NUMBER_NUMBER = 1;
    public static final int TRACK_NUMBER = 2;
    public static final int STAMP_NUMBER = 3;
    public static final int BEGIN_YEAR_NUMBER = 4;
    public static final int MILLAGE_NUMBER = 5;

    public List<Bus> parseBusArray(String[] data) throws IllegalInputException {
        List<Bus> list = new ArrayList<>();
        for (String data1 : data) {
            Bus bus = parseBus(data1);
            list.add(bus);
        }
        return list;

    }

    public Bus parseBus(String data) throws IllegalInputException {
        String[] splitData = data.split(SPLITER);
        if (splitData.length != ATTRIBUTE_COUNT) {
            throw new IllegalInputException();
        }
        String surname = splitData[SURNAME_NUMBER];
        int busNumber = Integer.parseInt(splitData[BUS_NUMBER_NUMBER]);
        int trackNumber = Integer.parseInt(splitData[TRACK_NUMBER]);
        String stamp = splitData[STAMP_NUMBER];
        int beginYear = Integer.parseInt(splitData[BEGIN_YEAR_NUMBER]);
        int millage = Integer.parseInt(splitData[MILLAGE_NUMBER]);

        return new Bus(surname, busNumber, trackNumber, stamp, beginYear, millage);

    }
}
