package by.epam.task13.controller.console;

import by.epam.task13.entities.Order;
import by.epam.task13.service.OrdersBuilder;
import by.epam.task13.service.impl.OrdersDomBuilder;
import by.epam.task13.service.impl.OrdersSaxBuilder;
import by.epam.task13.service.impl.OrdersStAXBuilder;
import by.epam.task13.service.validator.OrdersValidator;

import java.util.*;

public class Controller {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        OrdersValidator ov = new OrdersValidator();
        Locale current = new Locale("RU", "ru");

        while (!isExit) {
            ResourceBundle rb = ResourceBundle.getBundle("property.text", current);

            System.out.println(rb.getString("console.choose"));
            System.out.println(rb.getString("console.changelanguage"));
            switch (sc.nextLine()) {
                case "E":
                    isExit = true;
                    break;
                case "execute":
                    System.out.println(rb.getString("console.path"));
                    String path = sc.nextLine();

                    boolean valid = ov.isValid(path);
                    if (valid) {
                        System.out.println(rb.getString("console.correctxml"));

                        System.out.println(rb.getString("console.waytoparse"));
                        System.out.println("sax, stax, dom");

                        String s = sc.nextLine();

                        List<Order> orders = parseFile(s, path);
                        System.out.println(rb.getString("console.result"));
                        System.out.println(orders);
                    } else {
                        System.out.println(rb.getString("console.xmlincorrect"));
                    }

                    break;
                case "c":
                    current = changeLocale(current);
                    break;
                default:
                    System.out.println(rb.getString("console.inputincorrect"));
            }
        }
    }

    private static Locale changeLocale(Locale current) {
        if (current.getCountry().equalsIgnoreCase("ru")) {
            return new Locale("en", "US");
        } else {
            return new Locale("RU", "ru");
        }
    }

    private static List<Order> parseFile(String s, String path) {
        List<Order> orders;
        if (s.equalsIgnoreCase("sax")) {
            OrdersBuilder builder = new OrdersSaxBuilder();
            builder.buildListOrders(path);
            orders = builder.getOrders();
        } else if (s.equalsIgnoreCase("stax")) {
            OrdersBuilder builder = new OrdersStAXBuilder();
            builder.buildListOrders(path);
            orders = builder.getOrders();
        } else if (s.equalsIgnoreCase("dom")) {
            OrdersBuilder builder = new OrdersDomBuilder();
            builder.buildListOrders(path);
            orders = builder.getOrders();
        } else {
            orders = new ArrayList<>();
        }
        return orders;
    }
}
