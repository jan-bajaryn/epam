package by.epam.task13.service.impl;

import by.epam.task13.entities.Order;
import by.epam.task13.service.handler.OrderHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class OrdersSaxBuilder {

    private static final Logger log = LogManager.getLogger(OrdersSaxBuilder.class);



    private List<Order> orders;
    private OrderHandler sh;
    private XMLReader reader;

    public OrdersSaxBuilder() {
        sh = new OrderHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (SAXException e) {
            log.info(e.getMessage());
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void buildListOrders(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException | IOException e) {
            log.info(e.getMessage());
        }
        orders = sh.getOrders();
    }
}