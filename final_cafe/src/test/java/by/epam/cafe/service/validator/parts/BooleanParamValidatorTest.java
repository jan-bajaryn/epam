package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BooleanParamValidatorTest {
    private final BooleanParamValidator booleanParamValidator = BooleanParamValidator.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {true, true},
                {false, true},
                {null, false},
        };
    }

    @Test(description = "Test for BooleanParamValidator",
            dataProvider = "check")
    public void checkInput(Boolean input, Boolean result) {
        assertEquals(booleanParamValidator.isValid(input), (boolean) result);
    }
}