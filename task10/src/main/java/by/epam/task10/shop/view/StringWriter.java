package by.epam.task10.shop.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StringWriter {

    public void writeString(String fileName, String data) throws IOException {
        Files.write(Paths.get(fileName), data.getBytes());
    }
}
