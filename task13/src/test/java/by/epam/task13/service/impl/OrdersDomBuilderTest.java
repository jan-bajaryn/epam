package by.epam.task13.service.impl;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrdersDomBuilderTest {

    private OrdersDomBuilder ordersDomBuilder = new OrdersDomBuilder();

    @Test
    public void testBuildSetOrders() {
        ordersDomBuilder.buildListOrders("src/main/resources/orders.xml");
        System.out.println(ordersDomBuilder.getOrders());
    }
}