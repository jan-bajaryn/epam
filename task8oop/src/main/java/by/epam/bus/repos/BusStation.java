package by.epam.bus.repos;

import by.epam.bus.dao.Bus;

import java.util.List;

public class BusStation {
    List<Bus> busList;

    public BusStation(List<Bus> busList) {
        this.busList = busList;
    }

    public void printBusList() {
        for (Bus bus : busList) {
            System.out.println(bus);
        }

    }

}
