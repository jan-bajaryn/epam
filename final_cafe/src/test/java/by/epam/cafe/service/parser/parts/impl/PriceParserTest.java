package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PriceParserTest {

    private final PriceParser priceParser = new PriceParser();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"0", true, 0},
                {"1", true, 1},
                {"100", true, 100},
                {"50000000", true, 5_00_000_00},
                {"1000000000", true, 1_000_000_000},
                {"-1", false, null},
                {"-100", false, null},
                {"-10000000", false, null},
                {"Тысяча", false, null},
                {"Something", false, null},
                {"Thousand123", false, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, Integer endValue) {
        if (result) {
            assertEquals(priceParser.parse(input).get(), endValue);
        } else {
            assertFalse(priceParser.parse(input).isPresent());
        }
    }
}