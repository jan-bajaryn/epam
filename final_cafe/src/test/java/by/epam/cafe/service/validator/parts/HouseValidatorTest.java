package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HouseValidatorTest {

    private final HouseValidator houseValidator = HouseValidator.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Some text", true},
                {"", false},
                {null, false},
                {"any text", true},
        };
    }

    @Test(description = "Test for HouseValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(houseValidator.isValid(input), (boolean) result);
    }
}