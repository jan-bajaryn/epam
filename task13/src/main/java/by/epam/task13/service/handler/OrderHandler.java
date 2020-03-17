package by.epam.task13.service.handler;

import by.epam.task13.entities.DeliveryInf;
import by.epam.task13.entities.Order;
import by.epam.task13.entities.Product;
import by.epam.task13.entities.enums.OrderStatus;
import by.epam.task13.entities.enums.PaymentType;
import by.epam.task13.entities.enums.ProductSize;
import by.epam.task13.entities.enums.ProductType;
import by.epam.task13.service.OrderEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class OrderHandler extends DefaultHandler {

    private static final Logger log = LogManager.getLogger(OrderHandler.class);

    private OrderEnum currentEnum = null;
    private EnumSet<OrderEnum> withText;

    private List<Order> orders;
    private Order.Builder current;

    private Product.Builder curProd;
    private List<Product> products;

    private DeliveryInf.Builder deliveryInf;

    private List<String> ingredients;

    public OrderHandler() {
        orders = new ArrayList<>();
        products = new ArrayList<>();
        ingredients = new ArrayList<>();
        withText = EnumSet.range(OrderEnum.ORDER, OrderEnum.EMAIL);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {

        if (OrderEnum.ORDER.getValue().equals(localName)) {
//            current = new Order();
            current = Order.builder();
            current.id(Integer.valueOf(attrs.getValue(0)));
        } else if (OrderEnum.PRODUCT.getValue().equals(localName)) {
            curProd = Product.builder();
        } else if (OrderEnum.DELIVERY_INF.getValue().equals(localName)) {
            deliveryInf = DeliveryInf.builder();
        }
//        else if (OrderEnum.PRODUCTS.getValue().equals(localName)){
//            products = new ArrayList<>();
//        } else if (OrderEnum.INGREDIENTS.getValue().equals(localName)){
//            ingredients = new ArrayList<>();
//        }
        else {
            OrderEnum temp = OrderEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (OrderEnum.ORDER.getValue().equals(localName)) {
            orders.add(current.build());
            clearData();
        } else if (OrderEnum.PRODUCT.getValue().equals(localName)) {
            products.add(curProd.build());
            clearIngredients();
        } else if (OrderEnum.PRODUCTS.getValue().equals(localName)) {
            current.products(products);
        } else if (OrderEnum.DELIVERY_INF.getValue().equals(localName)) {
            current.deliveryInf(deliveryInf.build());
        } else if (OrderEnum.INGREDIENTS.getValue().equals(localName)) {
            curProd.ingredients(ingredients);
            clearIngredients();
        }
    }

    private void clearIngredients() {
        ingredients = new ArrayList<>();
    }

    private void clearData() {
        products = new ArrayList<>();
        deliveryInf = null;
        curProd = null;
        ingredients = new ArrayList<>();
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    curProd.name(s);
                    break;
                case DESCRIPTION:
                    curProd.description(s);
                    break;
                case PHOTO_NAME:
                    curProd.photoName(s);
                    break;
                case PRICE:
                    curProd.price(Integer.valueOf(s));
                    break;
                case PRODUCT_TYPE:
                    curProd.type(ProductType.valueOf(s.toUpperCase()));
                    break;
                case PRODUCT_SIZE:
                    curProd.size(ProductSize.valueOf(s.toUpperCase()));
                    break;
                case EMAIL:
                    deliveryInf.email(s);
                    break;
                case PHONE:
                    deliveryInf.phone(s);
                    break;
                case ADDRESS:
                    deliveryInf.address(s);
                    break;
                case CLIENT_NAME:
                    deliveryInf.clientName(s);
                    break;
                case DELIVERY_TIME:
                    deliveryInf.deliveryTime(LocalDateTime.parse(s));
                    break;
                case CREATION:
                    current.creation(LocalDateTime.parse(s));
                    break;
                case TOTAL_PRICE:
                    current.price(Integer.valueOf(s));
                    break;
                case STATUS:
                    current.status(OrderStatus.valueOf(s.toUpperCase()));
                    break;
                case PAYMENT_TYPE:
                    current.paymentType(PaymentType.values()[Integer.parseInt(s)]);
                    break;
                case INGREDIENT:
                    ingredients.add(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}