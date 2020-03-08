package by.epam.task13.service;

import by.epam.task13.entities.Order;

import java.util.List;

public interface OrdersBuilder {
    List<Order> getOrders();

    void buildListOrders(String fileName);
}
