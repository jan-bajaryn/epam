package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EmailValidatorTest {

    private final EmailValidator emailValidator = new EmailValidator();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Some text", false},
                {"", false},
                {null, false},
                {"input@gmail.com", true},
        };
    }

    @Test(description = "Test for EmailValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(emailValidator.isValid(input), (boolean) result);
    }
}