package by.epam.task12.service.parser;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ToIntArrayParserTest {

    private ToIntArrayParser toIntArrayParser = new ToIntArrayParser();

    @Test
    public void testParse() {
        int[][] parse = toIntArrayParser.parse(new String[]{
                "1---2---3---4",
                "1---2---2---2"
        });
        assertEquals(parse, new int[][]{
                {1, 2, 3, 4},
                {1, 2, 2, 2}
        });
    }
}