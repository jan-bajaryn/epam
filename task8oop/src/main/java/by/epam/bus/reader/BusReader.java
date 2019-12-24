package by.epam.bus.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BusReader {

    public String[] readBusesLines(String filename) throws IOException {
        return Files.lines(Paths.get(filename))
                .toArray(String[]::new);
    }

}
