package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RoomValidatorTest {

    private final RoomValidator roomValidator = new RoomValidator();


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

    @Test(description = "Test for RoomValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(roomValidator.isValid(input), (boolean) result);
    }
}