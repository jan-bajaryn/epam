package by.epam.task11.entities.factory;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.impl.Paragraph;
import by.epam.task11.entities.impl.Sentence;
import by.epam.task11.entities.impl.Text;
import by.epam.task11.entities.impl.Token;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.epam.task11.entities.CompType.*;

import static org.testng.Assert.*;

public class CompositeFactoryTest {

    private CompositeFactory compositeFactory = new CompositeFactory();

    @DataProvider(name = "base")
    public Object[][] base() {
        return new Object[][]{
                {
                        SENTENCE, Paragraph.class
                },
                {
                        PARAGRAPH, Text.class
                },
                {
                        TOKEN, Sentence.class
                },
                {
                        LETTER, Token.class
                },
        };
    }

    @Test(dataProvider = "base")
    public void testCreate(CompType type, Class cl) {
        assertSame(compositeFactory.create(type).getClass(), cl);
    }

    @Test(description = "test null for factory")
    public void testNull() {
        assertNull(compositeFactory.create(null));
    }
}