package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.db.impl.Product;

import java.util.Map;

/**
 * Dedicated to parse input parameters into {@link Product},
 * Check and return validity of input parameters
 */
public interface ProductParser {

    /**
     * @param redirect     Map to return what parameter is valid, and value with
     *                     what parameter was in input
     *                     First String in the map is the name of parameter
     *                     Second String in the map is value of input in parameter
     *                     or information about existing error in the map
     *                     For example {street, abcde} means that input for
     *                     parameter of name "street" was "abcde"
     *                     {street_error, true} means that in parameter
     *                     of name "street" was error.
     * @param productGroup Product group parameter, id of product group {@link Product#getProductGroup()}
     * @param price        Price parameter {@link Product#getPrice()}
     * @param weight       Weight parameter {@link Product#getWeight()}
     * @return {@link Product} with parsed parameters if all params are valid
     * or {@code null} if any of the parameter is invalid
     */
    Product parseProduct(Map<String, String> redirect, String productGroup, String price, String weight);

    /**
     * @param redirect     Map to return what parameter is valid, and value with
     *                     what parameter was in input
     *                     First String in the map is the name of parameter
     *                     Second String in the map is value of input in parameter
     *                     or information about existing error in the map
     *                     For example {street, abcde} means that input for
     *                     parameter of name "street" was "abcde"
     *                     {street_error, true} means that in parameter
     *                     of name "street" was error.     *
     * @param id           identifier of Product {@link Product#getId()}
     * @param productGroup identifier of ProductGroup in Product {@link Product#getProductGroup()}
     * @param price        Price parameter {@link Product#getPrice()}
     * @param weight       Weight parameter {@link Product#getWeight()}
     * @return {@link Product} with parsed parameters if all params are valid
     * or {@code null} if any of the parameter is invalid
     */
    Product parseProductWithId(Map<String, String> redirect, String id, String productGroup, String price, String weight);
}
