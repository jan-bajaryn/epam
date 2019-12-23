package by.epam.bus.controller;

import by.epam.bus.dao.Bus;
import by.epam.bus.repos.BusStation;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        BusReaderCommandd busReaderCommandd = new BusReaderCommandd();
        List<Bus> buses = busReaderCommandd.execute();
        BusStation busStation = new BusStation(buses);
        busStation.printBusList();
    }
}
