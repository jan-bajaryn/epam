package by.epam.task9.example1.textedit.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextFileReader {
    public String readLinesFromFile(String filename) throws IOException {
        return Files.readString(Paths.get(filename));
    }
}
