package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductGroupInProductParserImplTest {

private final ProductGroupInProductParser productGroupInProductParser =
        new ProductGroupInProductParser();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"0", false, null},
                {"1", true, 1},
                {"100", true, 100},
                {"1000000", true, 1_000_000},
                {"1_000_000", false, null},
                {Integer.toString(Integer.MAX_VALUE), true, Integer.MAX_VALUE},
                {Integer.toString(Integer.MIN_VALUE), false, null},
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
            assertEquals(productGroupInProductParser.parse(input).get(), endValue);
        } else {
            assertFalse(productGroupInProductParser.parse(input).isPresent());
        }
    }
}