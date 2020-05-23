package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UsernameParserTest {

    private final UsernameParser usernameParser = UsernameParser.getInstance();




    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Aaa", true,"Aaa"},
                {"   Aaa   ", true,"Aaa"},
                {"AakL", true,"AakL"},
                {"AakL   ", true,"AakL"},
                {"авпВаDE34", true,"авпВаDE34"},
                {"", false,null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(usernameParser.parse(input).get(), endValue);
        } else {
            assertFalse(usernameParser.parse(input).isPresent());
        }
    }
}