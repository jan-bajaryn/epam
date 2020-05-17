package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PhotoNameValidatorTest {

    private final PhotoNameValidator photoNameValidator = new PhotoNameValidator();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Мое название.jpg", true},
                {"Мое название.png", true},
                {"Мое название.jpeg", true},
                {"Мое название.gif", true},
                {"Что-то", false},
                {"Aba aba.jpg", true},
                {"Aba aba.png", true},
                {"Aba aba.jpeg", true},
                {"Aba aba", false},
                {null, false},
                {"", false},
        };
    }

    @Test(description = "Test for PhotoNameValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(photoNameValidator.isValid(input), (boolean) result);
    }
}