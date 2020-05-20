package by.epam.cafe.service.pagination.impl;

import by.epam.cafe.service.exception.PaginationException;
import by.epam.cafe.service.pagination.PaginationCalculator;

public class PaginationCalculatorImpl implements PaginationCalculator {

    /**
     * @param pagination String input to parse
     * @return int value of input if it can be parsed
     * @throws PaginationException if pagination is null or can't be parsed to int
     */
    @Override
    public int calculatePartParam(String pagination) throws PaginationException {
        if (pagination == null) {
            throw new PaginationException("Param is null");
        }
        try {
            int result = Integer.parseInt(pagination);
            if (result < 1) {
                throw new PaginationException("Param less than 1, param = " + result);
            }
            return result;
        } catch (NumberFormatException e) {
            throw new PaginationException("Can't parse param to int, param = " + pagination, e);
        }
    }
}
