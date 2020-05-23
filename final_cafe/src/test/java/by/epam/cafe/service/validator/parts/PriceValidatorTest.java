package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PriceValidatorTest {

    private final PriceValidator priceValidator = PriceValidator.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {0, true},
                {1, true},
                {100, true},
                {5_00_000_00, true},
                {1_000_000_000, true},
                {-1, false},
                {-100, false},
                {-10000000, false},
        };
    }

    @Test(description = "Test for PriceValidator",
            dataProvider = "check")
    public void checkInput(Integer input, Boolean result) {
        assertEquals(priceValidator.isValid(input), (boolean) result);
    }

    @Test(description = "Check for null input")
    public void checkNullInput() {
        assertThrows(NullPointerException.class, () -> priceValidator.isValid(null));
    }
}