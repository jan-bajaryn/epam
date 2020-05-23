package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdParserTest {

    private final IdParser idParser = IdParser.getInstance();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"0", false, null},
                {"1", true, 1},
                {"100", true, 100},
                {"10000000", true, 10000000},
                {"1000000000", true, 1000000000},
                {"-1", false, null},
                {"-100", false, null},
                {"-10000000", false, null},
                {Integer.toString(Integer.MAX_VALUE), true, Integer.MAX_VALUE},
                {Integer.toString(Integer.MIN_VALUE), false, null},
                {"Тысяча", false, null},
                {"Something", false, null},
                {"Thousand123", false, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, Integer endValue) {
        if (result) {
            assertEquals(idParser.parse(input).get(), endValue);
        } else {
            assertFalse(idParser.parse(input).isPresent());
        }
    }
}