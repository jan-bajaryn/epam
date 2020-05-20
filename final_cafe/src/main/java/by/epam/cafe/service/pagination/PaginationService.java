package by.epam.cafe.service.pagination;

import java.util.Map;

/**
 * Dedicated to calculate template for pagination
 */
public interface PaginationService {

    /**
     * @param allCount  count of all members in list of displayable objects
     * @param current   current page, current part of the list
     * @param pageLimit limit of element per page
     * @return {@link Map} with template to fill in the view to create pagination bar
     * Integer means number of position, {@link PaginationStatus} means how element must behave
     * @see PaginationStatus
     */
    Map<Integer, PaginationStatus> calculate(int allCount, int current, int pageLimit);
}
