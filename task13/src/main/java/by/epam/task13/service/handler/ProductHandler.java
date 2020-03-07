package by.epam.task13.service.handler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import by.epam.task13.entities.Product;
import by.epam.task13.service.ProductEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ProductHandler extends DefaultHandler {
    private Set<Product> products;
    private Product current = null;
    private ProductEnum currentEnum = null;
    private EnumSet<ProductEnum> withText;

    public ProductHandler() {
        products = new HashSet<Product>();
        withText = EnumSet.range(ProductEnum.NAME, ProductEnum.STREET);
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("student".equals(localName)) {
            current = new Product();
//            current.setLogin(attrs.getValue(0));
//            if (attrs.getLength() == 2) {
//                current.setFaculty(attrs.getValue(1));
//            }
        } else {
            ProductEnum temp = ProductEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("student".equals(localName)) {
            products.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
//                case TELEPHONE:
//                    current.setTelephone(Integer.parseInt(s));
//                    break;
//                case STREET:
//                    current.getAddress().setStreet(s);
//                    break;
//                case CITY:
//                    current.getAddress().setCity(s);
//                    break;
//                case COUNTRY:
//                    current.getAddress().setCountry(s);
//                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}