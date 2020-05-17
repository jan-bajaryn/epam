package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordValidatorTest {
    private final PasswordValidator passwordValidator = new PasswordValidator();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"1234", false},
                {"false", false},
                {"", false},
                {"K4doiuDDuchIha", true},
                {null, false},
        };
    }

    @Test(description = "Test for PasswordValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(passwordValidator.isValid(input), (boolean) result);
    }

}