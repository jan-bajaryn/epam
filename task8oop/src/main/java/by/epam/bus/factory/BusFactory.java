package by.epam.bus.factory;

import by.epam.bus.dao.Bus;
import by.epam.bus.dao.Person;
import by.epam.bus.factory.exception.IllegalBusInputException;
import by.epam.bus.validator.BusValidator;

public class BusFactory {

    private BusValidator busValidator;

    public BusFactory() {
        busValidator = new BusValidator();
    }

    public Bus create(Person driver,
                      int busNumber,
                      int trackNumber,
                      String stamp,
                      int beginYear,
                      int millage) throws IllegalBusInputException {
        Bus bus = new Bus(driver, busNumber, trackNumber, stamp, beginYear, millage);
        if (busValidator.isValid(bus)){
            return bus;
        } else {
            throw new IllegalBusInputException();
        }
    }
}
