package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SurnameParserTest {

    private final SurnameParser surnameParser = new SurnameParser();
    private static final String MORE_THAN_20 = "ффффффффффффффффффффф";


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Баранов", true, "Баранов"},
                {"   Баранов   ", true, "Баранов"},
                {"Баранов   ", true, "Баранов"},
                {"Акарошка", true, "Акарошка"},
                {"Вика-Хорошист", true, "Вика-Хорошист"},
                {"A", true, "A"},
                {"a", true, "a"},
                {MORE_THAN_20, false, null},
                {null, true, null},
                {"", true, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(surnameParser.parse(input).get(), endValue);
        } else {
            assertFalse(surnameParser.parse(input).isPresent());
        }
    }
}