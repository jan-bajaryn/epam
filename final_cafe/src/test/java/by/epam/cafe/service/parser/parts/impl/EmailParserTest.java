package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.enums.OrderStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.epam.cafe.entity.enums.OrderStatus.*;
import static by.epam.cafe.entity.enums.OrderStatus.DELIVERING;
import static org.testng.Assert.*;

public class EmailParserTest {

    private final EmailParser emailParser = new EmailParser();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false, null},
                {" abc@gmail.com  ", true, "abc@gmail.com"},
                {" abcgmail.com  ", false, null},
                {"aaa@mail.ru", true, "aaa@mail.ru"},
                {"some@i.ii", false, null},
                {"Some value", false, null},
                {"waiting", false, null},
                {"delivering", false, null},
                {"что-то такое", false, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(emailParser.parse(input).get(), endValue);
        } else {
            assertFalse(emailParser.parse(input).isPresent());
        }
    }
}