package by.epam.task13.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class OrdersStAXBuilderTest {

    private static final Logger log = LogManager.getLogger(OrdersStAXBuilderTest.class);


    @Test
    public void testBuildListOrders() {
        OrdersStAXBuilder ordersStAxBuilder = new OrdersStAXBuilder();
        ordersStAxBuilder.buildListOrders("src/main/resources/orders.xml");
//        log.info("ordersStAxBuilder.getOrders() = {}", ordersStAxBuilder.getOrders());
        System.out.println(ordersStAxBuilder.getOrders());
    }
}