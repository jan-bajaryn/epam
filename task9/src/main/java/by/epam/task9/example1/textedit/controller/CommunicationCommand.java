package by.epam.task9.example1.textedit.controller;

import by.epam.task9.example1.textedit.dao.ConsoleCommandReader;
import by.epam.task9.example1.textedit.dao.TextFileReader;
import by.epam.task9.example1.textedit.entity.Text;
import by.epam.task9.example1.textedit.factory.IllegalInputException;
import by.epam.task9.example1.textedit.parser.TextParser;

import java.io.IOException;

public class CommunicationCommand {

    private ConsoleCommandReader consoleCommandReader = new ConsoleCommandReader();
    private GemeralCommand gemeralCommand = new GemeralCommand();
    private TextParser textParser = new TextParser();
    private TextFileReader textFileReader = new TextFileReader();

    public void execute() {
        boolean isDone = false;
        while (!isDone) {
            switch (consoleCommandReader.readFirstCommand()) {
                case ConsoleCommandReader.READING_FILE:
                    try {
                        String fileName = consoleCommandReader.readFileName();
                        Text text = textParser.stringParseText(textFileReader.readLinesFromFile(fileName));
                        gemeralCommand.execute(text);
                    } catch (IllegalInputException e) {
                        System.out.println("Wrong format in file.");
                    } catch (IOException e) {
                        System.out.println("No such file found.");
                    }
                    break;
                case ConsoleCommandReader.SELF_INPUT:
                    try {
                        String result = consoleCommandReader.selfInput();
                        Text text = textParser.stringParseText(result);
                        gemeralCommand.execute(text);
                    } catch (IllegalInputException e) {
                        System.out.println("Format of text is incorrect.");
                    }
                    break;
                case ConsoleCommandReader.EXIT:
                    isDone = true;
                    break;
                default:
                    System.out.println("Your input is incorrect. Please try again.");
            }
        }
    }
}
