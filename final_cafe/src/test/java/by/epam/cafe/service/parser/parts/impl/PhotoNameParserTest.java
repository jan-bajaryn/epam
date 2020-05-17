package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PhotoNameParserTest {

    private final PhotoNameParser photoNameParser = new PhotoNameParser();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Мое название.jpg", true, "Мое название.jpg"},
                {"Мое название.png", true, "Мое название.png"},
                {"Мое название.jpeg", true, "Мое название.jpeg"},
                {"Мое название.gif", true, "Мое название.gif"},
                {"Что-то", false, null},
                {"Aba aba.jpg", true, "Aba aba.jpg"},
                {"Aba aba.png", true, "Aba aba.png"},
                {"Aba aba.jpeg", true, "Aba aba.jpeg"},
                {"Aba aba", false, null},
                {null, false, null},
                {"", false, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(photoNameParser.parse(input).get(), endValue);
        } else {
            assertFalse(photoNameParser.parse(input).isPresent());
        }
    }
}