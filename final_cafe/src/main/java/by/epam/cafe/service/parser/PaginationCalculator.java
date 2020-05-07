package by.epam.cafe.service.parser;

import by.epam.cafe.service.exception.PaginationException;

public interface PaginationCalculator {
    int calculatePartParam(String pagination) throws PaginationException;
}
