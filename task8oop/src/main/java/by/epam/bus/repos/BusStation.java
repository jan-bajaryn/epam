package by.epam.bus.repos;

import by.epam.bus.dao.Bus;
import by.epam.bus.dao.Person;
import by.epam.bus.repos.exception.IllegalMillageInputException;
import by.epam.bus.repos.exception.IllegalYearCountInputException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BusStation {

    private static BusStation busStation;

    static {
        busStation = new BusStation(new ArrayList<>());
    }

    public static BusStation getInstance() {
        return busStation;
    }

    private List<Bus> busList;

    private BusStation(List<Bus> busList) {
        this.busList = busList;
    }

    public String retBusesForPrint() {
        StringBuilder sb = new StringBuilder();
        for (Bus bus : busList) {
            sb.append(bus.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public List<Bus> busesByTrackNumber(int track) {
        return busList.stream()
                .filter(bus -> bus.getTrackNumber() == track)
                .collect(Collectors.toList());
    }

    public List<Bus> busesMoreExploitation(int yearCount) throws IllegalYearCountInputException {

        if (yearCount < 0) {
            throw new IllegalYearCountInputException();
        }

        final LocalDate localDate = LocalDate.now();
        return busList.stream()
                .filter(bus -> bus.getBeginYear() + yearCount > localDate.getYear())
                .collect(Collectors.toList());
    }

    public List<Bus> busesMoreMillage(int millage) throws IllegalMillageInputException {
        if (millage < 0) {
            throw new IllegalMillageInputException();
        }

        return busList.stream()
                .filter(bus -> bus.getMillage() > millage)
                .collect(Collectors.toList());

    }

    public List<Bus> busesByDriver(Person driver) {
        if (driver == null) {
            return new ArrayList<>();
        }
        return busList.stream()
                .filter(bus -> driver.equals(bus.getDriver()))
                .collect(Collectors.toList());

    }

    public void removeAll() {
        busList = new ArrayList<>();
    }

    public void setBase(List<Bus> busList) {
        this.busList = busList;
    }

}
