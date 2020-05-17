package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StreetValidatorTest {

    private final StreetValidator streetValidator = new StreetValidator();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Some text", true},
                {"", true},
                {null, true},
                {"any text", true},
        };
    }

    @Test(description = "Test for StreetValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(streetValidator.isValid(input), (boolean) result);
    }

}