package by.epam.cafe.service.parser.parts.impl;

import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.ProductType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.epam.cafe.entity.enums.ProductType.*;
import static org.testng.Assert.*;

public class ProductTypeParserTest {

    private final ProductTypeParser productTypeParser = ProductTypeParser.getInstance();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false, null},
                {PIZZA.name(), true, PIZZA},
                {DESSERT.name(), true, DESSERT},
                {DRINK.name(), true, DRINK},
                {SNACK.name(), true, SNACK},
                {"Some value", false, null},
                {"waiting", false, null},
                {"delivering", false, null},
                {"что-то такое", false, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, ProductType endValue) {
        if (result) {
            assertEquals(productTypeParser.parse(input).get(), endValue);
        } else {
            assertFalse(productTypeParser.parse(input).isPresent());
        }
    }
}