package by.epam.task09.example1.textedit.service;

import by.epam.task09.example1.textedit.entity.Sentence;
import by.epam.task09.example1.textedit.entity.Text;
import by.epam.task09.example1.textedit.factory.IllegalInputException;
import by.epam.task09.example1.textedit.factory.SentenceFactory;

public class AppendText {
    private SentenceFactory sentenceFactory = new SentenceFactory();

    public void appendSentence(Text text, String data) throws IllegalInputException {
        Sentence sentence = sentenceFactory.create(data);
        text.getSentences().add(sentence);
    }
}
