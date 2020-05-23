package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommentsParserTest {

    private static final String MORE_THAN_200_INPUT = "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj ";
    private static final String MORE_THAN_200_INPUT_WITH_SPACES = "                                            jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj ";
    private static final String MORE_THAN_200_INPUT_WITH_SPACES_RESULT = "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj";
    private final CommentsParser commentsParser = CommentsParser.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Some text", true, "Some text"},
                {" Some text   ", true, "Some text"},
                {" Some text  k ", true, "Some text  k"},
                {"", true, null},
                {null, true, null},
                {"Какой-то текст", true, "Какой-то текст"},
                {MORE_THAN_200_INPUT, false, null},
                {MORE_THAN_200_INPUT_WITH_SPACES, true, MORE_THAN_200_INPUT_WITH_SPACES_RESULT},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(commentsParser.parse(input).get(), endValue);
        } else {
            assertFalse(commentsParser.parse(input).isPresent());
        }
    }
}