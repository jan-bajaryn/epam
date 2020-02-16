package by.epam.task11.entities.factory;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Composite;
import by.epam.task11.entities.impl.Paragraph;
import by.epam.task11.entities.impl.Sentence;
import by.epam.task11.entities.impl.Text;
import by.epam.task11.entities.impl.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CompositeFactory {

    private static final Logger log = LogManager.getLogger(CompositeFactory.class);


    public Composite create(CompType type) {

        log.info("create with: type = {}", type);

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
