package by.epam.cafe.service.pagination.impl;

import by.epam.cafe.service.pagination.PaginationStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.TreeMap;

import static by.epam.cafe.service.pagination.PaginationStatus.*;
import static org.testng.Assert.*;

public class PaginationServiceImplTest {

    private final PaginationServiceImpl paginationServiceImpl = new PaginationServiceImpl();


    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {
                        20, 2, 8,
                        new TreeMap<Integer, PaginationStatus>() {
                            {
                                put(0, NORMAL);
                                put(1, NORMAL);
                                put(2, ACTIVE);
                                put(3, NORMAL);
                                put(4, NORMAL);
                            }
                        }
                },
                {
                        100, 4, 12,
                        new TreeMap<Integer, PaginationStatus>() {
                            {
                                put(0, NORMAL);
                                put(1, NORMAL);
                                put(2, THREE_POINTS);
                                put(3, NORMAL);
                                put(4, ACTIVE);
                                put(5, NORMAL);
                                put(6, NORMAL);
                                put(10, NORMAL);
                            }
                        }
                },
                {
                        -1, 4, 12,
                        new TreeMap<Integer, PaginationStatus>()
                },
                {
                        20, 2, 21,
                        new TreeMap<Integer, PaginationStatus>() {
                            {
                                put(0, NORMAL);
                                put(1, NORMAL);
                                put(2, NORMAL);
                            }
                        }
                },
                {
                        9, 19, 1,
                        new TreeMap<Integer, PaginationStatus>() {
                            {
                                put(0, NORMAL);
                                put(1, NORMAL);
                                put(2, NORMAL);
                                put(3, NORMAL);
                                put(4, NORMAL);
                                put(5, NORMAL);
                                put(6, NORMAL);
                                put(7, THREE_POINTS);
                                put(8, NORMAL);
                                put(10, NORMAL);
                            }
                        }
                },
                {
                        9, 9, 1,
                        new TreeMap<Integer, PaginationStatus>() {
                            {
                                put(0, NORMAL);
                                put(1, NORMAL);
                                put(2, THREE_POINTS);
                                put(6, NORMAL);
                                put(7, NORMAL);
                                put(8, NORMAL);
                                put(9, ACTIVE);
                                put(10, DISABLED);
                            }
                        }
                },
        };
    }

    @Test(description = "",
            dataProvider = "check")
    public void testCalculate(Integer allCount, Integer current, Integer pageLimit, Map<Integer, PaginationStatus> result) {
        Map<Integer, PaginationStatus> calculate = paginationServiceImpl.calculate(allCount, current, pageLimit);
        System.out.println("calculate = " + calculate);
        assertEquals(calculate, result);
    }
}