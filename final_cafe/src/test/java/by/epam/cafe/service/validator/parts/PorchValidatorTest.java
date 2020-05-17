package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PorchValidatorTest {

    private final PorchValidator porchValidator = new PorchValidator();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {1, true},
                {25, true},
                {49, true},
                {50, false},
                {0, false},
                {100, false},
                {10000000, false},
                {-1, false},
                {-100, false},
                {-10000000, false},
        };
    }

    @Test(description = "Test for PorchValidator",
            dataProvider = "check")
    public void checkInput(Integer input, Boolean result) {
        assertEquals(porchValidator.isValid(input), (boolean) result);
    }

    @Test(description = "Check for null input")
    public void checkNullInput() {
        assertThrows(NullPointerException.class, () -> porchValidator.isValid(null));
    }
}