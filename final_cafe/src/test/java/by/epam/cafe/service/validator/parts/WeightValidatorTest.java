package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WeightValidatorTest {

    private final WeightValidator weightValidator = new WeightValidator();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {0, false},
                {1, true},
                {100, true},
                {1_000_000, true},
                {Integer.MAX_VALUE, false},
                {Integer.MIN_VALUE, false},
                {-1, false},
                {-100, false},
                {-10000000, false},
        };
    }

    @Test(description = "Test for WeightValidator",
            dataProvider = "check")
    public void checkInput(Integer input, Boolean result) {
        assertEquals(weightValidator.isValid(input), (boolean) result);
    }
}