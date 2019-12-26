package by.epam.bus.service;

import by.epam.bus.entity.Bus;
import by.epam.bus.entity.Person;
import by.epam.bus.factory.BusFactory;
import by.epam.bus.factory.exception.IllegalBusInputException;
import by.epam.bus.factory.exception.IllegalPersonParamsException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomBusGenerator {
    public static final String STAMP_FILE = "bus_stamps.txt";
    public static final String NAME_FILE = "names.txt";
    public static final String SURNAME_FILE = "surnames.txt";
    public static final String FATHER_NAMES_FILE = "father_names.txt";
    public static final int BUS_NUMBER_MAX = 60;
    public static final int TRACK_NUMBER_MAX = 70;
    public static final int BEGIN_YEAR_MAX = 2019;
    public static final int BEGIN_YEAR_MIN = 1960;
    public static final int MILLAGE_MAX = 40;

    private PersonGenerator personGenerator;

    private BusFactory busFactory;

    public RandomBusGenerator() {
        busFactory = new BusFactory();
        personGenerator = new PersonGenerator();
    }

    public List<Bus> generate(int size) throws IOException, IllegalPersonParamsException, IllegalBusInputException {
        if (size < 1) {
            return new ArrayList<>();
        }
        List<Bus> buses = new ArrayList<>(size);
        Random r = new Random();
        List<String> stamps = Files.lines(Paths.get(STAMP_FILE)).collect(Collectors.toList());
        List<String> names = Files.lines(Paths.get(NAME_FILE)).collect(Collectors.toList());
        List<String> surnames = Files.lines(Paths.get(SURNAME_FILE)).collect(Collectors.toList());
        List<String> fatherNames = Files.lines(Paths.get(FATHER_NAMES_FILE)).collect(Collectors.toList());

        for (int i = 0; i < size; i++) {
            generateBus(buses, r, stamps, names, surnames, fatherNames);
        }
        return buses;
    }

    private void generateBus(List<Bus> buses,
                             Random r,
                             List<String> stamps,
                             List<String> names,
                             List<String> surnames,
                             List<String> fatherNames) throws IllegalPersonParamsException, IllegalBusInputException {
        Person person = personGenerator.generatePerson(names, surnames, fatherNames);
        buses.add(busFactory.create(
                person,
                r.nextInt(BUS_NUMBER_MAX),
                r.nextInt(TRACK_NUMBER_MAX),
                stamps.get(r.nextInt(stamps.size())),
                (r.nextInt(BEGIN_YEAR_MAX - BEGIN_YEAR_MIN) + BEGIN_YEAR_MIN),
                r.nextInt(MILLAGE_MAX))
        );
    }
}
