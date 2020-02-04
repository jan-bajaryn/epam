package by.epam.task09.example1.textedit.controller;

import by.epam.task09.example1.textedit.dao.ConsoleCommandReader;
import by.epam.task09.example1.textedit.entity.Text;
import by.epam.task09.example1.textedit.factory.IllegalInputException;
import by.epam.task09.example1.textedit.service.AppendText;
import by.epam.task09.example1.textedit.view.PrintText;
import by.epam.task09.example1.textedit.view.WriteText;

import java.io.IOException;

public class GemeralCommand {

    private ConsoleCommandReader consoleCommandReader = new ConsoleCommandReader();
    private AppendText appendText = new AppendText();
    private PrintText printText = new PrintText();
    private WriteText writeText = new WriteText();

    public void execute(Text text) {
        boolean isDone = false;
        while (!isDone) {
            switch (consoleCommandReader.readSecondCommand()) {
                case ConsoleCommandReader.APPENT_TEXT:
                    try {
                        appendText.appendSentence(text, consoleCommandReader.readSentence());
                    } catch (IllegalInputException e) {
                        System.out.println("Format is incorrect.");
                    }
                    break;
                case ConsoleCommandReader.PRINT_CONSOLE:
                    printText.print(text);
                    break;
                case ConsoleCommandReader.PRINT_CONSOLE_HEADER:
                    printText.printHeader(text);
                    break;
                case ConsoleCommandReader.WRITE_FILE:
                    try {
                        writeText.write(consoleCommandReader.readFileName(), text);
                    } catch (IOException e) {
                        System.out.println("Problems with writing to file.");
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
