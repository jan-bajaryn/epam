package by.epam.task13.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import by.epam.task13.entities.DeliveryInf;
import by.epam.task13.entities.Order;
import by.epam.task13.entities.Product;
import by.epam.task13.entities.enums.OrderStatus;
import by.epam.task13.entities.enums.PaymentType;
import by.epam.task13.entities.enums.ProductSize;
import by.epam.task13.entities.enums.ProductType;
import by.epam.task13.service.OrderEnum;
import by.epam.task13.service.OrdersBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static by.epam.task13.service.OrderEnum.*;

public class OrdersDomBuilder implements OrdersBuilder {

    private static final Logger log = LogManager.getLogger(OrdersDomBuilder.class);


    private List<Order> orders;
    private DocumentBuilder docBuilder;

    public OrdersDomBuilder() {
        this.orders = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void buildListOrders(String fileName) {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList orderList = root.getElementsByTagName(ORDER.getValue());

            for (int i = 0; i < orderList.getLength(); i++) {
                Element orderElement = (Element) orderList.item(i);
                Order order = buildOrder(orderElement);
                orders.add(order);
            }
        } catch (IOException | SAXException e) {
            log.info(e.getMessage());
        }
    }

    private Order buildOrder(Element el) {
        Order order = new Order();
// заполнение объекта student

        order.setId(el.getAttribute("id"));
        order.setCreation(LocalDateTime.parse(getElementTextContent(el, CREATION.getValue())));
        order.setPrice(Integer.valueOf(getElementTextContent(el, TOTAL_PRICE.getValue())));
        order.setStatus(OrderStatus.valueOf(getElementTextContent(el, STATUS.getValue()).toUpperCase()));
        order.setPaymentType(PaymentType.values()[Integer.parseInt(getElementTextContent(el, PAYMENT_TYPE.getValue()))]);

        order.setDeliveryInf(buildDeliveryInf(((Element) el.getElementsByTagName(DELIVERY_INF.getValue()).item(0))));
        order.setProducts(buildProducts(el.getElementsByTagName(PRODUCT.getValue())));
        return order;
    }

    private List<Product> buildProducts(NodeList list) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            Product product = buildProduct(((Element) item));
            productList.add(product);
        }
        return productList;
    }

    private Product buildProduct(Element item) {
        Product product = new Product();
        product.setName(getElementTextContent(item, NAME.getValue()));
        product.setDescription(getElementTextContent(item, DESCRIPTION.getValue()));
        product.setPhotoName(getElementTextContent(item, PHOTO_NAME.getValue()));
        product.setPrice(Integer.valueOf(getElementTextContent(item, PRICE.getValue())));
        product.setType(ProductType.valueOf(getElementTextContent(item, PRODUCT_TYPE.getValue()).toUpperCase()));
        product.setSize(ProductSize.valueOf(getElementTextContent(item, PRODUCT_SIZE.getValue()).toUpperCase()));

        product.setIngredients(buildIngredients(item.getElementsByTagName(INGREDIENT.getValue())));
        return product;
    }

    private List<String> buildIngredients(NodeList list) {
        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            ingredients.add(list.item(i).getTextContent());
        }
        return ingredients;
    }

    private DeliveryInf buildDeliveryInf(Element item) {
        if (item == null) {
            return null;
        }
        DeliveryInf deliveryInf = new DeliveryInf();
        deliveryInf.setDeliveryTime(LocalDateTime.parse(getElementTextContent(item, DELIVERY_TIME.getValue())));
        deliveryInf.setClientName(getElementTextContent(item, CLIENT_NAME.getValue()));
        deliveryInf.setAddress(getElementTextContent(item, ADDRESS.getValue()));
        deliveryInf.setPhone(getElementTextContent(item, PHONE.getValue()));
        deliveryInf.setEmail(getIfPresent(item, EMAIL.getValue()));
        return deliveryInf;
    }

    private String getIfPresent(Element item, String value) {
        NodeList nList = item.getElementsByTagName(value);
        Node node = nList.item(0);
        if (node == null) {
            return null;
        }
        return node.getTextContent();
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
