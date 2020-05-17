package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductGroupDescriptionValidatorTest {

    private final ProductGroupDescriptionValidator productGroupDescriptionValidator = new ProductGroupDescriptionValidator();


    private static final String MORE_THAN_200_INPUT = "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj ";

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Some text", true},
                {"Какой-то текст", true},
                {"", false},
                {null, false},
                {MORE_THAN_200_INPUT, false},
        };
    }

    @Test(description = "Test for ProductGroupDescriptionValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(productGroupDescriptionValidator.isValid(input), (boolean) result);
    }
}