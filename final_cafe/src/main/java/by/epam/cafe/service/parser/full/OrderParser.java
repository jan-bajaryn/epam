package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.db.impl.Product;

import java.util.Map;

/**
 * Dedicated to parse input parameters into {@link Order},
 * Check and return validity of input parameters
 */
public interface OrderParser {
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
     * @param streetParam   Street parameter {@link DeliveryInf#getStreet()}
     * @param commentsParam Comments parameter {@link DeliveryInf#getComments()}
     * @param floorParam    Floor parameter {@link DeliveryInf#getFloor()}
     * @param porchParam    Porch parameter {@link DeliveryInf#getPorch()}
     * @param roomParam     Room parameter {@link DeliveryInf#getRoom()}
     * @param houseParam    House parameter {@link DeliveryInf#getHouse()}
     * @param nameParam     Name of client parameter {@link Order#getClientName()}
     * @param phoneParam    Phone parameter {@link DeliveryInf#getPhone()}
     * @param emailParam    Email parameter {@link DeliveryInf#getEmail()}
     * @param timeParam     Time parameter {@link DeliveryInf#getDeliveryTime()}
     * @param basket        count of products identified by ids {@link Order#getProducts()}
     * @param paymentTypeParam payment type parameter {@link Order#getPaymentType()}
     * @return {@link Order} if all parameters was valid, and {@code null} if any
     * parameter wasn't valid
     */
    Order parse(Map<String, String> redirect, String streetParam, String commentsParam, String floorParam, String porchParam, String roomParam, String houseParam, String nameParam, String phoneParam, String emailParam, String timeParam, Map<Product, Integer> basket, String paymentTypeParam);

    /**
     * Parse parameters and put after parsing parameters into base entity
     *
     * @param redirect      Map to return what parameter is valid, and value with
     *                      what parameter was in input
     *                      First String in the map is the name of parameter
     *                      Second String in the map is value of input in parameter
     *                      or information about existing error in the map
     *                      For example {street, abcde} means that input for
     *                      parameter of name "street" was "abcde"
     *                      {street_error, true} means that in parameter
     *                      of name "street" was error.
     * @param order         base to in what after parsing should be put results of parsing params
     * @param streetParam   Street parameter {@link DeliveryInf#getStreet()}
     * @param commentsParam Comments parameter {@link DeliveryInf#getComments()}
     * @param floorParam    Floor parameter {@link DeliveryInf#getFloor()}
     * @param porchParam    Porch parameter {@link DeliveryInf#getPorch()}
     * @param roomParam     Room parameter {@link DeliveryInf#getRoom()}
     * @param houseParam    House parameter {@link DeliveryInf#getHouse()}
     * @param nameParam     Name of client parameter {@link Order#getClientName()}
     * @param phoneParam    Phone parameter {@link DeliveryInf#getPhone()}
     * @param emailParam    Email parameter {@link DeliveryInf#getEmail()}
     * @param timeParam     Time parameter {@link DeliveryInf#getDeliveryTime()}
     * @param paymentTypeParam payment type parameter {@link Order#getPaymentType()}
     * @return true if parsing is successfully executed and all params is valid
     * otherwise returns false
     */
    boolean parseWithBase(Map<String, String> redirect, Order order, String streetParam, String commentsParam, String floorParam, String porchParam, String roomParam, String houseParam, String nameParam, String phoneParam, String emailParam, String timeParam, String paymentTypeParam);

    /**
     * Parse parameters and put after parsing parameters into base entity for Operator
     * {@link by.epam.cafe.entity.enums.Role#OPERATOR}
     *
     * @param redirect         Map to return what parameter is valid, and value with
     *                         what parameter was in input
     *                         First String in the map is the name of parameter
     *                         Second String in the map is value of input in parameter
     *                         or information about existing error in the map
     *                         For example {street, abcde} means that input for
     *                         parameter of name "street" was "abcde"
     *                         {street_error, true} means that in parameter
     *                         of name "street" was error.
     * @param order            base to in what after parsing should be put results of parsing params
     * @param streetParam      Street parameter {@link DeliveryInf#getStreet()}
     * @param commentsParam    Comments parameter {@link DeliveryInf#getComments()}
     * @param floorParam       Floor parameter {@link DeliveryInf#getFloor()}
     * @param porchParam       Porch parameter {@link DeliveryInf#getPorch()}
     * @param roomParam        Room parameter {@link DeliveryInf#getRoom()}
     * @param houseParam       House parameter {@link DeliveryInf#getHouse()}
     * @param nameParam        Name of client parameter {@link Order#getClientName()}
     * @param phoneParam       Phone parameter {@link DeliveryInf#getPhone()}
     * @param emailParam       Email parameter {@link DeliveryInf#getEmail()}
     * @param timeParam        Time parameter {@link DeliveryInf#getDeliveryTime()}
     * @param statusParam      Status parameter {@link Order#getStatus()}
     * @param paymentTypeParam Payment type parameter {@link Order#getPaymentType()}
     * @param priceParam       Price parameter {@link Order#getPrice()}
     * @return true if parsing is successfully executed and all params is valid
     * otherwise returns false
     */
    boolean parseForOperatorWithBase(Map<String, String> redirect, Order order, String streetParam, String commentsParam, String floorParam, String porchParam, String roomParam, String houseParam, String nameParam, String phoneParam, String emailParam, String timeParam, String statusParam, String paymentTypeParam, String priceParam);
}
