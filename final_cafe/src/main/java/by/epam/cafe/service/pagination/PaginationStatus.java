package by.epam.cafe.service.pagination;

/**
 * Dedicated to mark template for pagination
 *
 * @see PaginationService
 */
public enum PaginationStatus {
    /**
     * Means that element must not be clickable
     */
    DISABLED,
    /**
     * Element must be active
     */
    ACTIVE,
    /**
     * Element must be normal
     */
    NORMAL,
    /**
     * Element must contain three points ("...") and be not clickable
     */
    THREE_POINTS;
}
