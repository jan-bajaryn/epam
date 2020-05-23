package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PorchParserTest {

    private final PorchParser porchParser = PorchParser.getInstance();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"1", true, 1},
                {"25", true, 25},
                {"49", true, 49},
                {"50", false, null},
                {"0", false, null},
                {"100", false, null},
                {"10000000", false, null},
                {"-1", false, null},
                {"-100", false, null},
                {"-10000000", false, null},
                {Integer.toString(Integer.MAX_VALUE), false, null},
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
            assertEquals(porchParser.parse(input).get(), endValue);
        } else {
            assertFalse(porchParser.parse(input).isPresent());
        }
    }
}