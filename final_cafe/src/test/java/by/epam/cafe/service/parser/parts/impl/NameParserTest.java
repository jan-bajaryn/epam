package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NameParserTest {

    private final NameParser nameParser = NameParser.getInstance();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Ivanov  ", true, "Ivanov"},
                {"Kaeltas", true, "Kaeltas"},
                {"  Ваня ", true, "Ваня"},
                {"Ван-Хельсинг", true, "Ван-Хельсинг"},
                {"Ван-Хельсинг-Карен", true, "Ван-Хельсинг-Карен"},
                {"Ван,Хельсинг-Карен ", true, "Ван,Хельсинг-Карен"},
                {"43Iva", false, null},
                {"^ihaha", false, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(nameParser.parse(input).get(), endValue);
        } else {
            assertFalse(nameParser.parse(input).isPresent());
        }
    }
}