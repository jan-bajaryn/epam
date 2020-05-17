package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FloorParserTest {

    private final FloorParser floorParser = new FloorParser();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"0", true,0},
                {"1", true,1},
                {"-1", true,-1},
                {"-99", true,-99},
                {"-100", false,null},
                {"99", true,99},
                {"100", false,null},
                {"50", true,50},
                {"-50", true,-50},
                {"10000000", false,null},
                {"-10000000", false,null},
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
            assertEquals(floorParser.parse(input).get(), endValue);
        } else {
            assertFalse(floorParser.parse(input).isPresent());
        }
    }
}