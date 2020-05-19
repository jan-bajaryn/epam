package by.epam.cafe.service.parser.helper.impl;

import by.epam.cafe.service.exception.IllegalPathParamException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PathVarCalculatorImplTest {
    private final PathVarCalculatorImpl pathVarCalculatorImpl = new PathVarCalculatorImpl();

    @DataProvider(name = "forErrors")
    public Object[][] forErrorsProvider
            () {
        return new Object[][]{
                {null},
                {""},
                {"dlkfgjdflkgj"},
                {"something"},
                {"dfg/l"},
                {"dfg/"},
                {"dfg/jjj"},
                {"/"},
        };
    }

    @Test(description = "",
            dataProvider = "forErrors")
    public void testFindLastIntegerErrors(String input) {
        assertThrows(IllegalPathParamException.class, () -> pathVarCalculatorImpl.findLastInteger(input));
    }

    @DataProvider(name = "positive")
    public Object[][] positiveProvider
            () {
        return new Object[][]{
                {"dlkfgj/23", 23},
                {"dlkfgj/-23", -23},
                {"/0", 0},
                {";ldkfgj/1000", 1000},
                {";ldkfgj/-1000", -1000},
        };
    }

    @Test(description = "",
            dataProvider = "positive")
    public void testFindLastIntegerPositive(String input, Integer result) throws IllegalPathParamException {
        assertEquals(pathVarCalculatorImpl.findLastInteger(input), result);
    }

}