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


    private PersonFactory personFactory = new PersonFactory();
    private BusFactory busFactory = new BusFactory();


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

    public String[] busesToStringArr(List<Bus> list) {
        return list.stream()
                .map(this::busToString)
                .toArray(String[]::new);
    }

    public String busToString(Bus bus) {
        return personToString(bus.getDriver()) +
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

    public String personToString(Person person) {
        return person.getName() +
                SPLITER +
                person.getSurname() +
                SPLITER +
                person.getFatherName();
    }
}
