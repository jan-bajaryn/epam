package by.epam.bus.parser;

import by.epam.bus.entity.Bus;
import by.epam.bus.dao.BusParams;
import by.epam.bus.entity.Person;
import by.epam.bus.dao.PersonParams;
import by.epam.bus.factory.BusFactory;
import by.epam.bus.factory.PersonFactory;
import by.epam.bus.factory.exception.IllegalBusInputException;
import by.epam.bus.factory.exception.IllegalPersonParamsException;
import by.epam.bus.parser.exception.IllegalInputCountException;

import java.util.ArrayList;
import java.util.List;

public class BusParser {

    public static final String SPLITER = "---";

//    public static final int NAME_NUMBER = 0;
//    public static final int SURNAME_NUMBER = 1;
//    public static final int FAT_NAME_NUMBER = 2;
//    public static final int BUS_NUMBER_NUMBER = 3;
//    public static final int TRACK_NUMBER = 4;
//    public static final int STAMP_NUMBER = 5;
//    public static final int BEGIN_YEAR_NUMBER = 6;
//    public static final int MILLAGE_NUMBER = 7;
//    public static final int ATTRIBUTE_COUNT = 8;


    private PersonFactory personFactory;
    private BusFactory busFactory;

    public BusParser() {
        personFactory = new PersonFactory();
        busFactory = new BusFactory();
    }

    public List<Bus> parseBusArray(String[] dataArr) throws IllegalInputCountException,
            IllegalBusInputException,
            IllegalPersonParamsException {

        List<Bus> list = new ArrayList<>();
        for (String data : dataArr) {
            Bus bus = stringToBus(data);
            list.add(bus);
        }
        return list;

    }

    public Bus stringToBus(String data) throws IllegalInputCountException, IllegalPersonParamsException, IllegalBusInputException {
        String[] splitData = data.split(SPLITER);
        if (splitData.length != BusParams.values().length + PersonParams.values().length) {
            throw new IllegalInputCountException();
        }

        String name = splitData[PersonParams.NAME.getNumber()];
        String surname = splitData[PersonParams.SURNAME.getNumber()];
        String fatherName = splitData[PersonParams.FATHER_NAME.getNumber()];


        int busNumber = Integer.parseInt(splitData[BusParams.BUS_NUMBER.getNumber()]);
        int trackNumber = Integer.parseInt(splitData[BusParams.TRACK_NUMBER.getNumber()]);
        String stamp = splitData[BusParams.STAMP.getNumber()];
        int beginYear = Integer.parseInt(splitData[BusParams.BEGIN_YEAR.getNumber()]);
        int millage = Integer.parseInt(splitData[BusParams.MILLAGE.getNumber()]);

        Person driver = personFactory.create(name, surname, fatherName);
        return busFactory.create(driver, busNumber, trackNumber, stamp, beginYear, millage);
    }

    public String busToString(Bus bus) {
        return bus.getDriver() +
                SPLITER +
                bus.getBusNumber() +
                SPLITER +
                bus.getTrackNumber() +
                SPLITER +
                bus.getStamp() +
                SPLITER +
                bus.getBeginYear() +
                SPLITER +
                bus.getMillage();
    }
}
