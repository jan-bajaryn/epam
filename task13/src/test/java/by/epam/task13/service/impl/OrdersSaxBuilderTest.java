package by.epam.task13.service.impl;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrdersSaxBuilderTest {


    @Test
    public void testBuildListOrders() {
        OrdersSaxBuilder ordersSaxBuilder = new OrdersSaxBuilder();
        ordersSaxBuilder.buildListOrders("src/main/resources/orders.xml");

        System.out.println(ordersSaxBuilder.getOrders());
    }
}