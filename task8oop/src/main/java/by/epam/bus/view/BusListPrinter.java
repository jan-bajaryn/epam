package by.epam.bus.view;

import by.epam.bus.entity.Bus;
import by.epam.bus.dao.BusParams;
import by.epam.bus.entity.Person;
import by.epam.bus.dao.PersonParams;
import by.epam.bus.parser.BusParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BusListPrinter {

    private BusParser busParser;

    public BusListPrinter() {
        busParser = new BusParser();
    }

    public void printToFile(List<Bus> list, String fileName, String prefixMessage, String postfixMessage) throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append(prefixMessage);
        sb.append("\n");
        for (Bus bus : list) {
            sb.append("\n");
            Person driver = bus.getDriver();
            sb.append("Водитель: ");
            sb.append(PersonParams.NAME.getTitle()).append(": ").append(driver.getName()).append(" ");
            sb.append(PersonParams.SURNAME.getTitle()).append(": ").append(driver.getSurname()).append(" ");
            sb.append(PersonParams.FATHER_NAME.getTitle()).append(": ").append(driver.getFatherName());
            sb.append("\n");

            sb.append(BusParams.BUS_NUMBER.getTitle()).append(": ").append(bus.getBusNumber()).append("\n");
            sb.append(BusParams.TRACK_NUMBER.getTitle()).append(": ").append(bus.getTrackNumber()).append("\n");
            sb.append(BusParams.STAMP.getTitle()).append(": ").append(bus.getStamp()).append("\n");
            sb.append(BusParams.BEGIN_YEAR.getTitle()).append(": ").append(bus.getBeginYear()).append("\n");
            sb.append(BusParams.MILLAGE.getTitle()).append(": ").append(bus.getMillage()).append("\n");

            sb.append("\n");
        }
        sb.append("\n");
        sb.append(postfixMessage);
        Files.write(Paths.get(fileName), sb.toString().getBytes());
    }

    public void printToFile(List<Bus> list, String fileName, Person person, String prefixMessage, String postfixMessage) throws IOException {

        StringBuilder sb = new StringBuilder(prefixMessage);
        sb.append(PersonParams.NAME.getTitle()).append(": ").append(person.getName()).append("\n");
        sb.append(PersonParams.SURNAME.getTitle()).append(": ").append(person.getSurname()).append("\n");
        sb.append(PersonParams.FATHER_NAME.getTitle()).append(": ").append(person.getFatherName()).append("\n");
        sb.append(":\n");
        printToFile(list, fileName, sb.toString(), postfixMessage);
    }

    public void printEditable(List<Bus> buses, String fileName) throws IOException {
        Optional<String> reduce = Arrays.stream(busParser.busesToStringArr(buses))
                .reduce((s, str) -> s.concat("\n").concat(str));
        if (reduce.isPresent()) {

            String s = reduce.get();
            Files.write(Paths.get(fileName), s.getBytes());
        }
    }
}
