package by.epam.task09.example4.account.dao.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextFileReader {
    public String[] readLinesFromFile(String filename) throws IOException {
        return Files.lines(Paths.get(filename))
                .toArray(String[]::new);
    }
}
