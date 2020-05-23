package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HouseParserTest {

    private final HouseParser houseParser = HouseParser.getInstance();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Some text", true, "Some text"},
                {"", false, null},
                {null, false, null},
                {"any text", true, "any text"},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(houseParser.parse(input).get(), endValue);
        } else {
            assertFalse(houseParser.parse(input).isPresent());
        }
    }
}