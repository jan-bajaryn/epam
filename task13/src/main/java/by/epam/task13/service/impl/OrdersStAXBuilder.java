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

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.epam.task13.service.OrderEnum.*;

public class OrdersStAXBuilder implements OrdersBuilder {

    private static final Logger log = LogManager.getLogger(OrdersStAXBuilder.class);


    private List<Order> orders = new ArrayList<>();

    private XMLInputFactory inputFactory;


    public OrdersStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void buildListOrders(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (valueOf(name.toUpperCase()) == ORDER) {
                        Order st = buildOrder(reader);
                        orders.add(st);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException ex) {
            log.info("buildListOrders: ex.getMessage() = {}", ex.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.info("buildListOrders: e.getMessage() = {}", e.getMessage());
            }
        }
    }

    private Order buildOrder(XMLStreamReader reader) throws XMLStreamException {
        Order or = new Order();
        or.setId(Integer.valueOf(reader.getAttributeValue(null, "id")));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (valueOf(name.toUpperCase())) {
                        case CREATION:
                            or.setCreation(LocalDateTime.parse(getXMLText(reader)));
                            break;
                        case TOTAL_PRICE:
                            or.setPrice(Integer.valueOf(getXMLText(reader)));
                            break;
                        case STATUS:
                            or.setStatus(OrderStatus.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case PAYMENT_TYPE:
                            or.setPaymentType(PaymentType.values()[Integer.parseInt(getXMLText(reader))]);
                            break;
                        case DELIVERY_INF:
                            or.setDeliveryInf(buildDeliveryInf(reader));
                            break;
                        case PRODUCTS:
                            or.setProducts(buildProducts(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (valueOf(name.toUpperCase()) == ORDER) {

                        if (or.getProducts() == null) {
                            or.setProducts(new ArrayList<>());
                        }

                        return or;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Order");
    }

    private List<Product> buildProducts(XMLStreamReader reader) throws XMLStreamException {

        List<Product> products = new ArrayList<>();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    if (valueOf(name.toUpperCase()) == PRODUCT) {
                        products.add(buildSingleProduct(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (valueOf(name.toUpperCase()) == PRODUCTS) {
                        return products;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Order");
    }

    private Product buildSingleProduct(XMLStreamReader reader) throws XMLStreamException {
        Product product = new Product();

        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (valueOf(name.toUpperCase())) {
                        case NAME:
                            product.setName(getXMLText(reader));
                            break;
                        case DESCRIPTION:
                            product.setDescription(getXMLText(reader));
                            break;
                        case PHOTO_NAME:
                            product.setPhotoName(getXMLText(reader));
                            break;
                        case PRICE:
                            product.setPrice(Integer.valueOf(getXMLText(reader)));
                            break;
                        case PRODUCT_TYPE:
                            product.setType(ProductType.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case PRODUCT_SIZE:
                            product.setSize(ProductSize.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case INGREDIENTS:
                            product.setIngredients(buildIngredients(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (valueOf(name.toUpperCase()) == PRODUCT) {
                        if (product.getIngredients() == null) {
                            product.setIngredients(new ArrayList<>());
                        }
                        return product;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag " + DELIVERY_INF.getValue());

    }

    private List<String> buildIngredients(XMLStreamReader reader) throws XMLStreamException {
        List<String> ingredients = new ArrayList<>();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    if (valueOf(name.toUpperCase()) == INGREDIENT) {
                        ingredients.add(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (valueOf(name.toUpperCase()) == INGREDIENTS) {
                        return ingredients;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag " + DELIVERY_INF.getValue());

    }

    private DeliveryInf buildDeliveryInf(XMLStreamReader reader) throws XMLStreamException {
        DeliveryInf di = new DeliveryInf();

        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (valueOf(name.toUpperCase())) {
                        case DELIVERY_TIME:
                            di.setDeliveryTime(LocalDateTime.parse(getXMLText(reader)));
                            break;
                        case CLIENT_NAME:
                            di.setClientName(getXMLText(reader));
                            break;
                        case ADDRESS:
                            di.setAddress(getXMLText(reader));
                            break;
                        case PHONE:
                            di.setPhone(getXMLText(reader));
                            break;
                        case EMAIL:
                            di.setEmail(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (valueOf(name.toUpperCase()) == DELIVERY_INF) {
                        return di;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag " + DELIVERY_INF.getValue());
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
