package by.epam.task13.service.impl;

import by.epam.task13.entities.DeliveryInf;
import by.epam.task13.entities.Order;
import by.epam.task13.entities.Product;
import by.epam.task13.entities.enums.OrderStatus;
import by.epam.task13.entities.enums.PaymentType;
import by.epam.task13.entities.enums.ProductSize;
import by.epam.task13.entities.enums.ProductType;
import by.epam.task13.service.OrdersBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.epam.task13.service.OrderEnum.*;

public class OrdersDomBuilder implements OrdersBuilder {

    private static final Logger log = LogManager.getLogger(OrdersDomBuilder.class);


    private List<Order> orders;
    private DocumentBuilder docBuilder;

    public OrdersDomBuilder() {
        this.orders = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
//            SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
//            Schema schema = xsdFactory.newSchema(new File("D:\\Programming\\epam\\epam\\task13\\src\\main\\resources\\orders.xsd"));
//            factory.setSchema(schema);

            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException /*| SAXException*/ e) {
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
        return Order.builder()
                .id(Integer.valueOf(el.getAttribute("id")))
                .creation(LocalDateTime.parse(getElementTextContent(el, CREATION.getValue())))
                .price(Integer.valueOf(getElementTextContent(el, TOTAL_PRICE.getValue())))
                .status(OrderStatus.valueOf(getElementTextContent(el, STATUS.getValue()).toUpperCase()))
                .paymentType(PaymentType.values()[Integer.parseInt(getElementTextContent(el, PAYMENT_TYPE.getValue()))])
                .deliveryInf(buildDeliveryInf(((Element) el.getElementsByTagName(DELIVERY_INF.getValue()).item(0))))
                .products(buildProducts(el.getElementsByTagName(PRODUCT.getValue())))
                .build();
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
        String name = item.getAttribute(PRODUCT_TYPE.getValue()).toUpperCase();
        ProductType type = null;
        if (!name.isEmpty()) {
            type = ProductType.valueOf(name);
        }

        return Product.builder()
                .id(Long.valueOf(calcId(item.getAttribute(ID.getValue()))))
                .type(type)
                .name(getElementTextContent(item, NAME.getValue()))
                .description(getElementTextContent(item, DESCRIPTION.getValue()))
                .photoName(getElementTextContent(item, PHOTO_NAME.getValue()))
                .price(Integer.valueOf(getElementTextContent(item, PRICE.getValue())))
                .size(ProductSize.valueOf(getElementTextContent(item, PRODUCT_SIZE.getValue()).toUpperCase()))
                .ingredients(buildIngredients(item.getElementsByTagName(INGREDIENT.getValue()))).build();
    }

    private String calcId(String attribute) {
        return attribute.substring(3);
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
        return DeliveryInf.builder()
                .deliveryTime(LocalDateTime.parse(getElementTextContent(item, DELIVERY_TIME.getValue())))
                .clientName(getElementTextContent(item, CLIENT_NAME.getValue()))
                .address(getElementTextContent(item, ADDRESS.getValue()))
                .phone(getElementTextContent(item, PHONE.getValue()))
                .email(getIfPresent(item, EMAIL.getValue())).build();
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
