package by.epam.task11.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderDao {
    public String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
