package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductGroupNameValidatorTest {

    private final ProductGroupNameValidator productGroupNameValidator = ProductGroupNameValidator.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Something", true},
                {"Текст", true},
                {"Ваня", true},
                {"Пицца-фарина", true},
                {"F", true},
                {"Ф", true},
                {null, false},
                {"", false},
        };
    }

    @Test(description = "Test for ProductGroupNameValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(productGroupNameValidator.isValid(input), (boolean) result);
    }

}