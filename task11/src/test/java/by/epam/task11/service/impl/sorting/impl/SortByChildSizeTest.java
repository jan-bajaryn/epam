package by.epam.task11.service.impl.sorting.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Component;
import by.epam.task11.entities.Composite;
import by.epam.task11.service.impl.chain.AbstractHandler;
import by.epam.task11.service.impl.chain.impl.ParagraphHandler;
import by.epam.task11.service.impl.chain.impl.SentenceHandler;
import by.epam.task11.service.impl.chain.impl.TokenHandler;
import by.epam.task11.service.impl.finder.ComponentsByTypeFinder;
import by.epam.task11.service.impl.finder.ComponentsByTypeFinderImpl;
import by.epam.task11.service.impl.sorting.CompositeSort;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SortByChildSizeTest {

    public static final String FINAL_DATA = "  Bye.\n" +
            "  Aaa bbb ccc. Abc dce, dft.\n" +
            "  Acd. Bfd. Abd.\n" +
            "  Aaa. Aaa. Aaa. Bcd.";
    public static final String TEST_DATA = "  Aaa bbb ccc. Abc dce, dft.\n" +
            "  Aaa. Aaa. Aaa. Bcd.\n" +
            "  Acd. Bfd. Abd.\n" +
            "  Bye.";

    private AbstractHandler abstractHandler;
    private CompositeSort compositeSort = new SortByChildSize();

    private Composite composite;
    private Composite finalComposite;

    @BeforeClass
    public void before() {
        abstractHandler = new ParagraphHandler(new SentenceHandler(new TokenHandler()));
        composite = abstractHandler.chain(TEST_DATA);
        finalComposite = abstractHandler.chain(FINAL_DATA);
    }

    @Test
    public void testFind() {
        compositeSort.sort(composite, CompType.PARAGRAPH);
        assertEquals(composite.operation(), finalComposite.operation());
    }
}