package by.epam.task10.shop.dao.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LinesReader {
    public String[] readFile(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
                .toArray(String[]::new);
    }
}
