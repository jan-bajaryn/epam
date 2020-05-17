package by.epam.cafe.service.parser.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BooleanParserTest {

    private final BooleanParser booleanParser = new BooleanParser();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"1", true, true},
                {"dfglkj", true, false},
                {"2", true, false},
                {"0", true, false},
                {null, true, false},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, Boolean endValue) {
        if (result) {
            assertEquals(booleanParser.parse(input).get(), endValue);
        } else {
            assertFalse(booleanParser.parse(input).isPresent());
        }
    }
}