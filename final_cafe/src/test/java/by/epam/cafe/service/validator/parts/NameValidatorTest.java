package by.epam.cafe.service.validator.parts;

import org.apache.ibatis.jdbc.Null;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NameValidatorTest {

    private final NameValidator nameValidator = new NameValidator();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Ivanov", true},
                {"Kaeltas", true},
                {"Ваня", true},
                {"Ван-Хельсинг", true},
                {"Ван-Хельсинг-Карен", true},
                {"Ван,Хельсинг-Карен", true},
                {"43Iva", false},
                {"^ihaha", false},
        };
    }

    @Test(description = "Test for NameValidator",
            dataProvider = "check")
    public void checkInput(String input, Boolean result) {
        assertEquals(nameValidator.isValid(input), (boolean) result);
    }

    @Test
    public void checkNull() {
        assertThrows(NullPointerException.class, () -> nameValidator.isValid(null));
    }
}