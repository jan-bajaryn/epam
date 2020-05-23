package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.db.impl.User;

import java.util.Map;

/**
 * Dedicated to parse input parameters into {@link User},
 * Check and return validity of input parameters
 */
public interface UserParser {
    /**
     * @param redirect      Map to return what parameter is valid, and value with
     *                      what parameter was in input
     *                      First String in the map is the name of parameter
     *                      Second String in the map is value of input in parameter
     *                      or information about existing error in the map
     *                      For example {street, abcde} means that input for
     *                      parameter of name "street" was "abcde"
     *                      {street_error, true} means that in parameter
     *                      of name "street" was error.
     * @param usernameParam {@link User#getUsername()}
     * @param passwordParam {@link User#getPassword()}
     * @param roleParam     {@link User#getRole()}
     * @param nameParam     {@link User#getName()}
     * @param surnameParam  {@link User#getSurname()}
     * @param houseParam    {@link User#getHouse()}
     * @param roomParam     {@link User#getRoom()}
     * @param porchParam    {@link User#getPorch()}
     * @param floorParam    {@link User#getFloor()}
     * @param phoneParam    {@link User#getPhone()}
     * @param emailParam    {@link User#getEmail()}
     * @param streetParam   {@link User#getStreet()}
     * @return entity {@link User} with parsed parameter, or {@code null} if any parameter
     * is invalid
     */
    User parseUser(Map<String, String> redirect, String usernameParam, String passwordParam, String roleParam, String nameParam, String surnameParam, String houseParam, String roomParam, String porchParam, String floorParam, String phoneParam, String emailParam, String streetParam);

    /**
     * @param redirect      Map to return what parameter is valid, and value with
     *                      what parameter was in input
     *                      First String in the map is the name of parameter
     *                      Second String in the map is value of input in parameter
     *                      or information about existing error in the map
     *                      For example {street, abcde} means that input for
     *                      parameter of name "street" was "abcde"
     *                      {street_error, true} means that in parameter
     *                      of name "street" was error.
     * @param id            {@link User#getId()}
     * @param usernameParam {@link User#getUsername()}
     * @param passwordParam {@link User#getPassword()}
     * @param roleParam     {@link User#getRole()}
     * @param nameParam     {@link User#getName()}
     * @param surnameParam  {@link User#getSurname()}
     * @param houseParam    {@link User#getHouse()}
     * @param roomParam     {@link User#getRoom()}
     * @param porchParam    {@link User#getPorch()}
     * @param floorParam    {@link User#getFloor()}
     * @param phoneParam    {@link User#getPhone()}
     * @param emailParam    {@link User#getEmail()}
     * @param streetParam   {@link User#getStreet()}
     * @param isBlocked     {@link User#isBlocked()}
     * @return entity {@link User} with parsed parameter, or {@code null} if any parameter
     * is invalid
     */
    User parseUserWithId(Map<String, String> redirect, String id, String usernameParam, String passwordParam, String roleParam, String nameParam, String surnameParam, String houseParam, String roomParam, String porchParam, String floorParam, String phoneParam, String emailParam, String streetParam, String isBlocked);

    /**
     * @param redirect      Map to return what parameter is valid, and value with
     *                      what parameter was in input
     *                      First String in the map is the name of parameter
     *                      Second String in the map is value of input in parameter
     *                      or information about existing error in the map
     *                      For example {street, abcde} means that input for
     *                      parameter of name "street" was "abcde"
     *                      {street_error, true} means that in parameter
     *                      of name "street" was error.
     * @param emailParam    {@link User#getEmail()}
     * @param phoneParam    {@link User#getPhone()}
     * @param usernameParam {@link User#getUsername()}
     * @param passwordParam {@link User#getPassword()}
     * @param nameParam     {@link User#getName()}
     * @param surnameParam  {@link User#getSurname()}
     * @param streetParam   {@link User#getStreet()}
     * @param houseParam    {@link User#getHouse()}
     * @param roomParam     {@link User#getRoom()}
     * @param porchParam    {@link User#getPorch()}
     * @param floorParam    {@link User#getFloor()}
     * @return entity {@link User} with parsed parameter, or {@code null} if any parameter
     * is invalid
     */
    User parseRegistrationUser(Map<String, String> redirect, String emailParam, String phoneParam, String usernameParam, String passwordParam, String nameParam, String surnameParam, String streetParam, String houseParam, String roomParam, String porchParam, String floorParam);

    User parseRegistrationUserWithToken(Map<String, String> redirect, String emailParam, String phoneParam, String usernameParam, String passwordParam, String nameParam, String surnameParam, String streetParam, String houseParam, String roomParam, String porchParam, String floorParam, String token);

}
