package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductGroupNameParserTest {

    private final ProductGroupNameParser productGroupNameParser = new ProductGroupNameParser();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"   Something   ", true, "Something"},
                {"Текст", true, "Текст"},
                {"Ваня", true, "Ваня"},
                {"  П ицца-фарина", true, "П ицца-фарина"},
                {"F", true, "F"},
                {"Ф", true, "Ф"},
                {null, false, null},
                {"", false, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(productGroupNameParser.parse(input).get(), endValue);
        } else {
            assertFalse(productGroupNameParser.parse(input).isPresent());
        }
    }
}