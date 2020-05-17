package by.epam.cafe.service.parser.full;

import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;
import by.epam.cafe.entity.db.impl.DeliveryInf;
import by.epam.cafe.entity.db.impl.Order;
import by.epam.cafe.entity.db.impl.Product;
import by.epam.cafe.entity.struct.OptionalNullable;
import by.epam.cafe.service.parser.helper.impl.ValidateAndPutter;
import by.epam.cafe.service.parser.parts.impl.*;
import by.epam.cafe.service.validator.BasketValidator;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderParser {

    private final StreetParser streetParser = new StreetParser();
    private final CommentsParser commentsParser = new CommentsParser();
    private final FloorParser floorParser = new FloorParser();
    private final PorchParser porchParser = new PorchParser();
    private final RoomParser roomParser = new RoomParser();
    private final HouseParser houseParser = new HouseParser();
    private final NameParser nameParser = new NameParser();
    private final PhoneParser phoneParser = new PhoneParser();
    private final EmailParser emailParser = new EmailParser();
    private final TimeParser timeParser = new TimeParser();
    private final PriceParser priceParser = new PriceParser();
    private final IdParser idParser = new IdParser();

    private final OrderStatusForOperatorParser orderStatusForOperatorParser = new OrderStatusForOperatorParser();
    private final PaymentTypeParser paymentTypeParser = new PaymentTypeParser();

    private final BasketValidator basketValidator = new BasketValidator();

    private final ValidateAndPutter validateAndPutter = ValidateAndPutter.getInstance();


    public Order parse(Map<String, String> redirect, String streetParam, String commentsParam, String floorParam, String porchParam, String roomParam, String houseParam, String nameParam, String phoneParam, String emailParam, String timeParam, Map<Product, Integer> basket) {
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> house = houseParser.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> email = emailParser.parse(emailParam);
        OptionalNullable<String> street = streetParser.parse(streetParam);
        OptionalNullable<String> comments = commentsParser.parse(commentsParam);
        OptionalNullable<LocalDateTime> time = timeParser.parse(timeParam);

        boolean result = validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPutter.validateAndPut(redirect, phone, "tel", phoneParam) &
                validateAndPutter.validateAndPut(redirect, email, "email", emailParam) &
                validateAndPutter.validateAndPut(redirect, comments, "comments", commentsParam) &
                validateAndPutter.validateAndPut(redirect, time, "time", timeParam) &
                validateAndPutter.validateAndPut(redirect, street, "street", streetParam);

        if (result && basketValidator.isValid(basket, redirect, "basket")) {

            DeliveryInf deliveryInf = DeliveryInf.newBuilder()
                    .porch(porch.get())
                    .deliveryTime(time.get())
                    .comments(comments.get())
                    .email(email.get())
                    .phone(phone.get())
                    .floor(floor.get())
                    .house(house.get())
                    .room(room.get())
                    .street(street.get())
                    .build();

            return Order.newBuilder()
                    .creation(LocalDateTime.now())
                    .clientName(name.get())
                    .paymentType(PaymentType.CASH)
                    .deliveryInf(deliveryInf)
                    .status(OrderStatus.CONFIRMED)
                    .price(calcSum(basket))
                    .products(basket)
                    .user(null)
                    .build();

        } else {
            return null;
        }
    }

    private Integer calcSum(Map<Product, Integer> basket) {
        return basket.entrySet().stream()
                .map(e -> e.getKey().getPrice() * e.getValue())
                .reduce(0, Integer::sum);
    }

    public boolean parseWithBase(Map<String, String> redirect, Order order, String streetParam, String commentsParam, String floorParam, String porchParam, String roomParam, String houseParam, String nameParam, String phoneParam, String emailParam, String timeParam) {
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> house = houseParser.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> email = emailParser.parse(emailParam);
        OptionalNullable<String> street = streetParser.parse(streetParam);
        OptionalNullable<String> comments = commentsParser.parse(commentsParam);
        OptionalNullable<LocalDateTime> time = timeParser.parse(timeParam);

        boolean result = validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPutter.validateAndPut(redirect, phone, "tel", phoneParam) &
                validateAndPutter.validateAndPut(redirect, email, "email", emailParam) &
                validateAndPutter.validateAndPut(redirect, comments, "comments", commentsParam) &
                validateAndPutter.validateAndPut(redirect, time, "time", timeParam) &
                validateAndPutter.validateAndPut(redirect, street, "street", streetParam);

        if (result && basketValidator.isValid(order.getProducts(), redirect, "basket")) {

            DeliveryInf deliveryInf = DeliveryInf.newBuilder()
                    .porch(porch.get())
                    .deliveryTime(time.get())
                    .comments(comments.get())
                    .email(email.get())
                    .phone(phone.get())
                    .floor(floor.get())
                    .house(house.get())
                    .room(room.get())
                    .street(street.get())
                    .build();

            order.setCreation(LocalDateTime.now());
            order.setClientName(name.get());
            order.setPaymentType(PaymentType.CASH);
            order.setDeliveryInf(deliveryInf);
            order.setStatus(OrderStatus.CONFIRMED);
            order.setPrice(calcSum(order.getProducts()));

            return true;
        } else {
            return false;
        }
    }

    public boolean parseForOperatorWithBase(Map<String, String> redirect, Order order, String streetParam, String commentsParam, String floorParam, String porchParam, String roomParam, String houseParam, String nameParam, String phoneParam, String emailParam, String timeParam, String statusParam, String paymentTypeParam, String priceParam) {
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> house = houseParser.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> email = emailParser.parse(emailParam);
        OptionalNullable<String> street = streetParser.parse(streetParam);
        OptionalNullable<String> comments = commentsParser.parse(commentsParam);
        OptionalNullable<LocalDateTime> time = timeParser.parse(timeParam);
        OptionalNullable<Integer> price = priceParser.parse(priceParam);
        OptionalNullable<OrderStatus> status = orderStatusForOperatorParser.parse(statusParam);
        OptionalNullable<PaymentType> paymentType = paymentTypeParser.parse(paymentTypeParam);

        boolean result = validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPutter.validateAndPut(redirect, phone, "tel", phoneParam) &
                validateAndPutter.validateAndPut(redirect, email, "email", emailParam) &
                validateAndPutter.validateAndPut(redirect, comments, "comments", commentsParam) &
                validateAndPutter.validateAndPut(redirect, time, "time", timeParam) &
                validateAndPutter.validateAndPut(redirect, street, "street", streetParam) &
                validateAndPutter.validateAndPut(redirect, price, "price", priceParam) &
                validateAndPutter.validateAndPut(redirect, status, "status", statusParam) &
                validateAndPutter.validateAndPut(redirect, paymentType, "payment_type", paymentTypeParam);

        if (result && basketValidator.isValid(order.getProducts(), redirect, "basket")) {

            DeliveryInf deliveryInf = order.getDeliveryInf();

            deliveryInf.setPorch(porch.get());
            deliveryInf.setDeliveryTime(time.get());
            deliveryInf.setComments(comments.get());
            deliveryInf.setEmail(email.get());
            deliveryInf.setPhone(phone.get());
            deliveryInf.setFloor(floor.get());
            deliveryInf.setHouse(house.get());
            deliveryInf.setRoom(room.get());
            deliveryInf.setStreet(street.get());

            order.setCreation(LocalDateTime.now());
            order.setClientName(name.get());
            order.setPaymentType(PaymentType.CASH);
            order.setDeliveryInf(deliveryInf);
            order.setStatus(OrderStatus.CONFIRMED);
            order.setPrice(price.get());
            order.setStatus(status.get());
            order.setPaymentType(paymentType.get());


            return true;
        } else {
            return false;
        }
    }
}
