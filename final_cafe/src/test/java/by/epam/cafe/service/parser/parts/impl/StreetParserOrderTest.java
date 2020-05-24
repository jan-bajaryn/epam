package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StreetParserOrderTest {

    private final StreetParserOrder streetParserOrder = StreetParserOrder.getInstance();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Some text  ", true, "Some text"},
                {"", true, null},
                {null, true, null},
                {"any text", true, "any text"},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(streetParserOrder.parse(input).get(), endValue);
        } else {
            assertFalse(streetParserOrder.parse(input).isPresent());
        }
    }
}