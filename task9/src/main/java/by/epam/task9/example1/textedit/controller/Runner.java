package by.epam.task9.example1.textedit.controller;

import by.epam.task9.example1.textedit.dao.TextFileReader;
import by.epam.task9.example1.textedit.entity.Text;
import by.epam.task9.example1.textedit.parser.TextParser;
import by.epam.task9.example1.textedit.view.PrintText;
import by.epam.task9.example1.textedit.factory.IllegalInputException;
import by.epam.task9.example1.textedit.factory.TextFactory;
import by.epam.task9.example1.textedit.service.AppendText;
import by.epam.task9.example1.textedit.service.HeaderOfText;
import by.epam.task9.example1.textedit.view.WriteText;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) {
        TextFactory textFactory = new TextFactory();
        PrintText printText = new PrintText();
        TextFileReader textFileReader = new TextFileReader();
        TextParser textParser = new TextParser();
        AppendText appendText = new AppendText();
        HeaderOfText headerOfText = new HeaderOfText();
        WriteText writeText = new WriteText();
        try {
            Text text = textFactory.create("Abla", "Akarosho wyhal ika. Purashymi. Lalala iii, jjj? III.");
            printText.print(text);
        } catch (IllegalInputException e) {
            System.out.println("problems");
        }

        try {
            Text text = textParser.stringParseText(textFileReader.readLinesFromFile("text.txt"));
            printText.print(text);
            appendText.appendSentence(text, "My very big brother very likes you, but ldldld kfkfkg.");
            printText.print(text);
            System.out.println(headerOfText.findHeader(text));
            writeText.write("hahaha.txt", text);
        } catch (IOException | IllegalInputException e) {
            e.printStackTrace();
        }

    }
}
