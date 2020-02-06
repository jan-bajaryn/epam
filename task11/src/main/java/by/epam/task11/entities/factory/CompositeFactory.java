package by.epam.task11.entities.factory;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Composite;
import by.epam.task11.entities.impl.Paragraph;
import by.epam.task11.entities.impl.Sentence;
import by.epam.task11.entities.impl.Text;
import by.epam.task11.entities.impl.Token;

public class CompositeFactory {
    public Composite create(CompType type) {
        switch (type) {
            case SENTENCE:
                return new Paragraph();
            case PARAGRAPH:
                return new Text();
            case TOKEN:
                return new Sentence();
            case LETTER:
                return new Token();
            default:
                return null;
        }
    }
}
