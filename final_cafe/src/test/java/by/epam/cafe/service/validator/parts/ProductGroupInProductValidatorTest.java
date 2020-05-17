package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductGroupInProductValidatorTest {

    private final ProductGroupInProductValidator productGroupInProductValidator =
            new ProductGroupInProductValidator();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {0, false},
                {1, true},
                {100, true},
                {10000000, true},
                {1000000000, true},
                {Integer.MAX_VALUE, true},
                {Integer.MIN_VALUE, false},
                {-1, false},
                {-100, false},
                {-10000000, false},
        };
    }

    @Test(description = "Test for ProductGroupInProductValidator",
            dataProvider = "check")
    public void checkInput(Integer input, Boolean result) {
        assertEquals(productGroupInProductValidator.isValid(input), (boolean) result);
    }
}