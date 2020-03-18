package by.epam.task13.consolecontroller.controller.command;

import by.epam.task13.consolecontroller.controller.Controller;
import by.epam.task13.consolecontroller.controller.command.dialog.Request;
import by.epam.task13.consolecontroller.controller.command.dialog.Response;
import by.epam.task13.entities.Order;
import by.epam.task13.service.OrdersBuilder;
import by.epam.task13.service.impl.OrdersDomBuilder;
import by.epam.task13.service.impl.OrdersSaxBuilder;
import by.epam.task13.service.impl.OrdersStAXBuilder;
import by.epam.task13.service.validator.OrdersValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ParseXmlCommand implements ExecCommand {


    private static final String SAX = "sax";
    private static final String STAX = "stax";
    private static final String DOM = "dom";

    @Override
    public Response execute(Request request) {

        ResourceBundle rb = ResourceBundle.getBundle("property.text", Controller.locale);

        Response response = new Response();
        response.setNextRequest(request);

        if (checkNulls(request.getMethod(), request.getFileNameMatrix())) {
            response.setDisplayInformation(rb.getString("consoleinput.illegalinput"));
            return response;
        }

        try {
            List<Order> orders = chooseAndExecute(request.getMethod(), request.getFileNameMatrix());

            response.setDisplayInformation(orders.toString());
        } catch (Exception e) {
            response.setDisplayInformation(rb.getString("consoleinput.illegalinput"));
        }

        return response;
    }

    @Override
    public String definition() {
        ResourceBundle rb = ResourceBundle.getBundle("property.text", Controller.locale);
        return rb.getString("consoleinput.parsexml");
    }

    private boolean checkNulls(String type, String path) {
        OrdersValidator ov = new OrdersValidator();
        return type == null || path == null || !ov.isValid(path);
    }

    private List<Order> chooseAndExecute(String type, String path) {
        List<Order> orders;
        if (type.equalsIgnoreCase(SAX)) {
            OrdersBuilder builder = new OrdersSaxBuilder();
            builder.buildListOrders(path);
            orders = builder.getOrders();
        } else if (type.equalsIgnoreCase(STAX)) {
            OrdersBuilder builder = new OrdersStAXBuilder();
            builder.buildListOrders(path);
            orders = builder.getOrders();
        } else if (type.equalsIgnoreCase(DOM)) {
            OrdersBuilder builder = new OrdersDomBuilder();
            builder.buildListOrders(path);
            orders = builder.getOrders();
        } else {
            orders = new ArrayList<>();
        }
        return orders;
    }

}
