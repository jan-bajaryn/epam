package by.epam.task13.service.impl;

import by.epam.task13.entities.Product;
import by.epam.task13.service.handler.ProductHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class OrdersSaxBuilder {
    private Set<Product> products;
    private ProductHandler sh;
    private XMLReader reader;

    public OrdersSaxBuilder() {
        sh = new ProductHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void buildSetStudents(String fileName) {
        try {
// разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        products = sh.getProducts();
    }
}