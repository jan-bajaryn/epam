package by.epam.task11.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportWriter {
    public void write(String fileName, String data) throws IOException {
        Files.write(Paths.get(fileName),data.getBytes());
    }
}
