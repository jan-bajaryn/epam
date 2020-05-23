package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PhoneParserTest {

    private final PhoneParser phoneParser = PhoneParser.getInstance();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"  888444333  ", true, "888444333"},
                {"1  110 00 000  ", true, "111000000"},
                {"0000 00000", true, "000000000"},
                {"0000 000010", false, null},
                {"lkj333", false, null},
                {"lkj", false, null},
                {"", false, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(phoneParser.parse(input).get(), endValue);
        } else {
            assertFalse(phoneParser.parse(input).isPresent());
        }
    }
}