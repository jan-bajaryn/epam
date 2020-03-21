package by.epam.cafe.entity.impl;

import by.epam.cafe.entity.Entity;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order extends Entity<Integer> {

    private LocalDate creation;

    private String clientName;

    private Integer price;

    private OrderStatus status;

    private PaymentType paymentType;

    private DeliveryInf deliveryInf;

    private List<Product> products = new ArrayList<>();

    private User user;

}
