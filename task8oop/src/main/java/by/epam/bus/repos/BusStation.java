package by.epam.bus.repos;

import by.epam.bus.dao.Bus;

import java.util.List;

public class BusStation {
    List<Bus> busList;

    public BusStation(List<Bus> busList) {
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

}
