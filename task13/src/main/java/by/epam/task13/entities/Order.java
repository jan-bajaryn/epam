package by.epam.task13.entities;

import by.epam.task13.entities.enums.OrderStatus;
import by.epam.task13.entities.enums.PaymentType;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private Long id;

    private LocalDate creation;
    private Integer price;
    private OrderStatus status;
    private PaymentType paymentType;
    private DeliveryInf deliveryInf;
    private List<Product> products;
}
