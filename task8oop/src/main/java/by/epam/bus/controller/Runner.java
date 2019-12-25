package by.epam.bus.controller;

import by.epam.bus.dao.Bus;
import by.epam.bus.repos.BusStation;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        BusReaderCommand busReaderCommand = new BusReaderCommand();
        List<Bus> buses = busReaderCommand.execute();
        BusStation busStation = new BusStation(buses);
        System.out.println(busStation.retBusesForPrint());
    }
}
