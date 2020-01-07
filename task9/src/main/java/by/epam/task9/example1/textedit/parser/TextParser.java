package by.epam.task9.example1.textedit.parser;

import by.epam.task9.example1.textedit.entity.*;
import by.epam.task9.example1.textedit.factory.IllegalInputException;
import by.epam.task9.example1.textedit.factory.TextFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TextParser {

    private TextFactory textFactory = new TextFactory();

    public String textParseString(Text text) {
        StringBuilder sb = new StringBuilder();
        sb.append("Head: \n").append(text.getHead());
        sb.append("\nSentences: \n");
        List<Sentence> sentences = text.getSentences();
        List<List<TextMember>> collect = sentences.stream()
                .map(Sentence::getMembers)
                .collect(Collectors.toList());
        for (List<TextMember> textMembers : collect) {
            sb.append(textMembers.get(0));
            for (int j = 1; j < textMembers.size(); j++) {
                TextMember current = textMembers.get(j);
                if (current instanceof Word) {
                    sb.append(" ").append(current.textValue());
                } else if (current instanceof EndSentence) {
                    sb.append(current.textValue()).append(" ");
                } else {
                    sb.append(current.textValue());
                }
            }
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    public Text stringParseText(String data) throws IllegalInputException {
        if (data == null) {
            throw new IllegalInputException();
        }
        String[] split = data.split("\n");
        if (split.length != 4) {
            throw new IllegalInputException();
        }
        return textFactory.create(split[1], split[3]);
    }
}
