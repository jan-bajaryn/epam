package by.epam.task09.example1.textedit.view;

import by.epam.task09.example1.textedit.entity.Text;
import by.epam.task09.example1.textedit.parser.TextParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteText {

    private TextParser textParser = new TextParser();

    public void write(String fileName, Text text) throws IOException {
        Files.write(Paths.get(fileName), textParser.textParseString(text).getBytes());
    }
}
