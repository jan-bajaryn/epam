package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PhoneValidatorTest {

    private final PhoneValidator phoneValidator = new PhoneValidator();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"888444333", true},
                {"111000000", true},
                {"000000000", true},
                {"lkj333", false},
                {"lkj", false},
                {"", false},
        };
    }

    @Test(description = "Test for PhoneValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(phoneValidator.isValid(input), (boolean) result);
    }

    @Test(description = "Check for null input")
    public void checkNullInput() {
        assertThrows(NullPointerException.class, () -> phoneValidator.isValid(null));
    }
}