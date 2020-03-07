package by.epam.task13.entities;

import by.epam.task13.entities.enums.OrderStatus;
import by.epam.task13.entities.enums.PaymentType;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private String id;

    private LocalDate creation;
    private Integer price;
    private OrderStatus status;
    private PaymentType paymentType;
    private DeliveryInf deliveryInf;
    private List<Product> products;

    public Order() {
    }

    public Order(String id,
                 LocalDate creation,
                 Integer price,
                 OrderStatus status,
                 PaymentType paymentType,
                 DeliveryInf deliveryInf,
                 List<Product> products) {
        this.id = id;
        this.creation = creation;
        this.price = price;
        this.status = status;
        this.paymentType = paymentType;
        this.deliveryInf = deliveryInf;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public void setCreation(LocalDate creation) {
        this.creation = creation;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public DeliveryInf getDeliveryInf() {
        return deliveryInf;
    }

    public void setDeliveryInf(DeliveryInf deliveryInf) {
        this.deliveryInf = deliveryInf;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", creation=" + creation +
                ", price=" + price +
                ", status=" + status +
                ", paymentType=" + paymentType +
                ", deliveryInf=" + deliveryInf +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (creation != null ? !creation.equals(order.creation) : order.creation != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (status != order.status) return false;
        if (paymentType != order.paymentType) return false;
        if (deliveryInf != null ? !deliveryInf.equals(order.deliveryInf) : order.deliveryInf != null) return false;
        return products != null ? products.equals(order.products) : order.products == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (creation != null ? creation.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (deliveryInf != null ? deliveryInf.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }

}
