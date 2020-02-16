package by.epam.task11.service.sorting.impl;

import by.epam.task11.entities.CompType;
import by.epam.task11.entities.Composite;
import by.epam.task11.service.chain.AbstractHandler;
import by.epam.task11.service.chain.impl.ParagraphHandler;
import by.epam.task11.service.chain.impl.SentenceHandler;
import by.epam.task11.service.chain.impl.TokenHandler;
import by.epam.task11.service.sorting.CompositeSort;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
    }


    @DataProvider(name = "checkDataParagraph")
    public Object[][] positiveDataParagraph() {
        return new Object[][]{
                {
                        "  Bye.\n" +
                                "  Aaa bbb ccc. Abc dce, dft.\n" +
                                "  Acd. Bfd. Abd.\n" +
                                "  Aaa. Aaa. Aaa. Bcd.",

                        "  Bye. \n" +
                                "  Aaa bbb ccc.  Abc dce, dft. \n" +
                                "  Acd.  Bfd.  Abd. \n" +
                                "  Aaa.  Aaa.  Aaa.  Bcd. "},
                {
                        "  Ccc aaa.\n" +
                                "Oo i.",
                        "  Ccc aaa.  Oo i. "
                },
                {
                        "Abc de.", "  Abc de. "
                },
                {
                        "  Bbb bbb ccc aaa kkk. Abc abc abc, abc?\n" +
                                "  Ppp Qdgf.",
                        "  Ppp Qdgf. \n" +
                                "  Bbb bbb ccc aaa kkk.  Abc abc abc, abc? "
                }
        };
    }

    @Test(description = "test for paragraphs",
            dataProvider = "checkDataParagraph")
    public void testFind(String testData, String finalData) {
        composite = abstractHandler.chain(testData);
        finalComposite = abstractHandler.chain(finalData);
        compositeSort.sort(composite, CompType.PARAGRAPH);
        assertEquals(composite.operation(), finalComposite.operation());
    }


    @DataProvider(name = "checkDataSentence")
    public Object[][] positiveData() {
        return new Object[][]{
                {
                        "  Bye.\n" +
                                "  Aaa bbb ccc. Abc dce, dft.\n" +
                                "  Acd. Bfd. Abd.\n" +
                                "  Aaa. Aaa. Aaa. Bcd.",

                        "  Bye. \n" +
                                "  Aaa bbb ccc.  Abc dce, dft. \n" +
                                "  Acd.  Bfd.  Abd. \n" +
                                "  Aaa.  Aaa.  Aaa.  Bcd. "},
                {
                        "  Ccc aaa.\n" +
                                "Oo i.",
                        "  Ccc aaa.  Oo i. "
                },
                {
                        "  Abc de.", "  Abc de."
                },
                {
                        "  Bbb bbb ccc aaa kkk. Abc abc abc, abc?\n" +
                                "  Ppp Qdgf.",
                        "  Bbb bbb ccc aaa kkk.  Abc abc abc, abc? \n" +
                                "  Ppp Qdgf. "
                }
        };
    }

    @Test(description = "test for sentences",
            dataProvider = "checkDataSentence")
    public void testSentence(String testData, String finalData) {
        composite = abstractHandler.chain(testData);
        finalComposite = abstractHandler.chain(finalData);
        compositeSort.sort(composite, CompType.SENTENCE);
        assertEquals(composite.operation(), finalComposite.operation());
    }


}