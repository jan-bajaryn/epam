package by.epam.cafe.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductGroupDescriptionParserTest {

    private final ProductGroupDescriptionParser productGroupDescriptionParser =
            ProductGroupDescriptionParser.getInstance();

    private static final String MORE_THAN_200_INPUT = "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj ";


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Some text  ", true, "Some text"},
                {"  Какой-то текст      ", true, "Какой-то текст"},
                {"Какой-то текст", true, "Какой-то текст"},
                {"", false, null},
                {null, false, null},
                {MORE_THAN_200_INPUT, false,null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(productGroupDescriptionParser.parse(input).get(), endValue);
        } else {
            assertFalse(productGroupDescriptionParser.parse(input).isPresent());
        }
    }
}