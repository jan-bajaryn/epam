package by.epam.cafe.service.pagination.impl;

import by.epam.cafe.service.exception.PaginationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PaginationCalculatorImplTest {

    private final PaginationCalculatorImpl paginationCalculatorImpl = new PaginationCalculatorImpl();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false, null},
                {"-1", false, null},
                {"0", false, null},
                {"-20000", false, null},
                {"asd", false, null},
                {"", false, null},
                {"12", true, 12},
                {"1", true, 1},
        };
    }

    @Test(description = "",
            dataProvider = "check")
    public void checkInput(String input, Boolean accept, Integer result) throws PaginationException {
        if (accept) {
            assertEquals(paginationCalculatorImpl.calculatePartParam(input), result.intValue());
        } else {
            assertThrows(PaginationException.class, () -> paginationCalculatorImpl.calculatePartParam(input));
        }
    }

}