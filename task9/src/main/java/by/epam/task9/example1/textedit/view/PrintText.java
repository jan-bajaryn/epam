package by.epam.task9.example1.textedit.view;

import by.epam.task9.example1.textedit.entity.Text;
import by.epam.task9.example1.textedit.parser.TextParser;

public class PrintText {
    private TextParser textParser = new TextParser();

    public void print(Text text) {
        System.out.println(textParser.textParseString(text));
    }


}
