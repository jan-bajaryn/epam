package by.epam.task10.calendar.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportWriter {
    public void write(String fileName, String data) throws IOException {
        Files.write(Paths.get(fileName),data.getBytes());
    }
}
