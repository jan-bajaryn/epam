package by.epam.task11.service.finder;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;
import by.epam.task11.service.chain.AbstractHandler;
import by.epam.task11.service.chain.impl.ParagraphHandler;
import by.epam.task11.service.chain.impl.SentenceHandler;
import by.epam.task11.service.chain.impl.TokenHandler;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ComponentsByTypeFinderImplTest {

    public static final String TEST_DATA = "  It was popularised in the with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\n" +
            "  It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.\n" +
            "  It is a established fact that a reader will be of a page when looking at its layout.\n" +
            "  Bye.";
    public static final String EMPTY = "";

    private AbstractHandler abstractHandler;

    private Composite composite;
    private ComponentsByTypeFinder componentsByTypeFinder = new ComponentsByTypeFinderImpl();

    @BeforeClass
    public void before() {
        abstractHandler = new ParagraphHandler(new SentenceHandler(new TokenHandler()));
        composite = abstractHandler.chain(TEST_DATA);
    }


    @DataProvider(name = "checkData")
    public Object[][] positiveData() {
        return new Object[][]{
                {CompType.PARAGRAPH, 1},
                {CompType.SENTENCE, 4},
                {CompType.LETTER, 121},
                {CompType.LEAF, 594},
                {CompType.TOKEN, 6},
        };
    }


    @Test(
            description = "Positive scenario for finder",
            dataProvider = "checkData")
    public void testFind(CompType type, int size) {
        List<Component> components = componentsByTypeFinder.find(type, composite);
        assertEquals(components.size(), size);
    }

    @Test(description = "Null input in CompType parameter")
    public void testNull() {
        List<Component> components = componentsByTypeFinder.find(null, composite);
        assertEquals(components.size(), 0);
    }

    @Test
    public void emptyTest() {
        Composite chain = abstractHandler.chain(EMPTY);
        List<Component> components = componentsByTypeFinder.find(CompType.LEAF, chain);
        assertEquals(components.size(), 0);
    }
}