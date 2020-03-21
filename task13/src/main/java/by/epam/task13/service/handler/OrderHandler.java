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

import static by.epam.task13.service.OrderEnum.*;

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
        withText = EnumSet.range(ORDER, EMAIL);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {

        if (ORDER.getValue().equals(localName)) {
            current = Order.builder();
            current.id(Integer.valueOf(attrs.getValue(0)));
        } else if (PRODUCT.getValue().equals(localName)) {
            curProd = Product.builder();

            curProd.id(Long.valueOf(calcId(attrs.getValue(ID.getValue()))));

            String value = attrs.getValue(PRODUCT_TYPE.getValue());
            ProductType pt = null;
            if (value != null) {
                pt = ProductType.valueOf(value.toUpperCase());
            }
            curProd.type(pt);

        } else if (DELIVERY_INF.getValue().equals(localName)) {
            deliveryInf = DeliveryInf.builder();
        }
        else {
            OrderEnum temp = valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }


    private String calcId(String attribute) {
        return attribute.substring(3);
    }

    public void endElement(String uri, String localName, String qName) {
        if (ORDER.getValue().equals(localName)) {
            orders.add(current.build());
            clearData();
        } else if (PRODUCT.getValue().equals(localName)) {
            products.add(curProd.build());
            clearIngredients();
        } else if (PRODUCTS.getValue().equals(localName)) {
            current.products(products);
        } else if (DELIVERY_INF.getValue().equals(localName)) {
            current.deliveryInf(deliveryInf.build());
        } else if (INGREDIENTS.getValue().equals(localName)) {
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
//                case PRODUCT_TYPE:
//                    curProd.type(ProductType.valueOf(s.toUpperCase()));
//                    break;
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