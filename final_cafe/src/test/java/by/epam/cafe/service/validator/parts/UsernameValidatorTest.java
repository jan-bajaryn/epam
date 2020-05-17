package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UsernameValidatorTest {

    private final UsernameValidator usernameValidator = new UsernameValidator();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Aaa", true},
                {"AakL", true},
                {"авпВаDE34", true},
                {"", false},
        };
    }

    @Test(description = "Test for UsernameValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(usernameValidator.isValid(input), (boolean) result);
    }

    @Test(description = "Check for null input")
    public void checkNullInput() {
        assertThrows(NullPointerException.class, () -> usernameValidator.isValid(null));
    }
}