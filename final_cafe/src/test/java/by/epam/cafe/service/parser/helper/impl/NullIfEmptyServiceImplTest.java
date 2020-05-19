package by.epam.cafe.service.parser.helper.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.NumberFormat;

import static org.testng.Assert.*;

public class NullIfEmptyServiceImplTest {

    private final NullIfEmptyServiceImpl nullIfEmptyServiceImpl = new NullIfEmptyServiceImpl();

    @DataProvider(name = "checkString")
    public Object[][] checkStringProvider
            () {
        return new Object[][]{
                {"", null},
                {null, null},
                {"abc", "abc"},
                {"a lskjdsf 3l 480987  bc", "a lskjdsf 3l 480987  bc"},
        };
    }

    @Test(description = "",
            dataProvider = "checkString")
    public void testNullIfEmptyString(String input, String result) {
        assertEquals(nullIfEmptyServiceImpl.nullIfEmptyString(input), result);
    }


    @DataProvider(name = "checkInteger")
    public Object[][] checkIntegerProvider
            () {
        return new Object[][]{
                {"", null},
                {null, null},
                {"1", 1},
                {"2", 2},
                {"-222", -222},
        };
    }

    @Test(description = "",
            dataProvider = "checkInteger")
    public void testNullIfEmptyInteger(String input, Integer result) {
        assertEquals(nullIfEmptyServiceImpl.nullIfEmptyInteger(input), result);
    }

    @Test
    public void testNullIfEmptyIntegerNumberFormat() {
        assertThrows(NumberFormatException.class, () -> nullIfEmptyServiceImpl.nullIfEmptyInteger("abc"));
    }

}