package by.epam.task12.dao.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LinesReader {

    public String[] read(String fileNameArr) throws IOException {
        return Files.lines(Paths.get(fileNameArr)).toArray(String[]::new);
    }
}
