package by.epam.cafe.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SurnameValidatorTest {

    private static final String MORE_THAN_20 = "ффффффффффффффффффффф";
    private final SurnameValidator surnameValidator = new SurnameValidator();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Баранов", true},
                {"Акарошка", true},
                {"Вика-Хорошист", true},
                {"A", true},
                {"a", true},
                {MORE_THAN_20, false},
                {null, true},
                {"", false},
        };
    }

    @Test(description = "Test for SurnameValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(surnameValidator.isValid(input), (boolean) result);
    }
}