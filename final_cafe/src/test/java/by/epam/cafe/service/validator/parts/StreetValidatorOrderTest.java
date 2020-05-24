package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StreetValidatorOrderTest {

    private final StreetValidatorOrder streetValidatorOrder = StreetValidatorOrder.getInstance();


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
        assertEquals(streetValidatorOrder.isValid(input), (boolean) result);
    }

}