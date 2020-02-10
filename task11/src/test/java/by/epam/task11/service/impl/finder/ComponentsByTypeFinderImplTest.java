package by.epam.task11.service.impl.finder;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;
import by.epam.task11.service.impl.chain.AbstractHandler;
import by.epam.task11.service.impl.chain.impl.ParagraphHandler;
import by.epam.task11.service.impl.chain.impl.SentenceHandler;
import by.epam.task11.service.impl.chain.impl.TokenHandler;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ComponentsByTypeFinderImplTest {

    public static final String TEST_DATA = "  It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\n" +
            "  It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.\n" +
            "  It is a established fact that a reader will be of a page when looking at its layout.\n" +
            "  Bye.";

    private AbstractHandler abstractHandler;

    private Composite composite;
    private ComponentsByTypeFinder componentsByTypeFinder = new ComponentsByTypeFinderImpl();

    @BeforeClass
    public void before() {
        abstractHandler = new ParagraphHandler(new SentenceHandler(new TokenHandler()));
        composite = abstractHandler.chain(TEST_DATA);
    }

    @Test
    public void testFind() {
        List<Component> components = componentsByTypeFinder.find(CompType.PARAGRAPH, composite);
        assertEquals(components.size(), 1);
    }

    @Test
    public void testFind2() {
        List<Component> components = componentsByTypeFinder.find(CompType.SENTENCE, composite);
        assertEquals(components.size(), 4);
    }
}