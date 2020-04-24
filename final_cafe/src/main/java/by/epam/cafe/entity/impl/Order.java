package by.epam.cafe.entity.impl;

import by.epam.cafe.entity.Entity;
import by.epam.cafe.entity.enums.OrderStatus;
import by.epam.cafe.entity.enums.PaymentType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order extends Entity<Integer> implements Serializable {


    private LocalDateTime creation;

    private String clientName;

    private Integer price;

    private OrderStatus status;

    private PaymentType paymentType;

    private DeliveryInf deliveryInf;

    private User user;

    private Map<Product,Integer> products = new HashMap<>();
//    private List<Product> products = new ArrayList<>();

    public Order() {
    }

    public Order(Integer integer, LocalDateTime creation, String clientName, Integer price, OrderStatus status, PaymentType paymentType, DeliveryInf deliveryInf, User user, Map<Product,Integer> products) {
        super(integer);
        this.creation = creation;
        this.clientName = clientName;
        this.price = price;
        this.status = status;
        this.paymentType = paymentType;
        this.deliveryInf = deliveryInf;
        this.user = user;
        this.products = products;
    }

    private Order(Builder builder) {
        setId(builder.id);
        setCreation(builder.creation);
        setClientName(builder.clientName);
        setPrice(builder.price);
        setStatus(builder.status);
        setPaymentType(builder.paymentType);
        setDeliveryInf(builder.deliveryInf);
        setUser(builder.user);
        setProducts(builder.products);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (creation != null ? !creation.equals(order.creation) : order.creation != null) return false;
        if (clientName != null ? !clientName.equals(order.clientName) : order.clientName != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (status != order.status) return false;
        if (paymentType != order.paymentType) return false;
        if (deliveryInf != null ? !deliveryInf.equals(order.deliveryInf) : order.deliveryInf != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        return products != null ? products.equals(order.products) : order.products == null;
    }

    @Override
    public int hashCode() {
        int result = creation != null ? creation.hashCode() : 0;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + (deliveryInf != null ? deliveryInf.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product,Integer> getProducts() {
        return products;
    }


    public void setProducts(Map<Product,Integer> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                " id=" + getId() +
                ", creation=" + creation +
                ", clientName='" + clientName + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", paymentType=" + paymentType +
                ", deliveryInf=" + deliveryInf +
                ", user=" + user +
                ", products=" + products +
                '}';
    }

    public static final class Builder {
        private Integer id;
        private LocalDateTime creation;
        private String clientName;
        private Integer price;
        private OrderStatus status;
        private PaymentType paymentType;
        private DeliveryInf deliveryInf;
        private User user;
        private Map<Product,Integer> products = new HashMap<>();

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder creation(LocalDateTime val) {
            creation = val;
            return this;
        }

        public Builder clientName(String val) {
            clientName = val;
            return this;
        }

        public Builder price(Integer val) {
            price = val;
            return this;
        }

        public Builder status(OrderStatus val) {
            status = val;
            return this;
        }

        public Builder paymentType(PaymentType val) {
            paymentType = val;
            return this;
        }

        public Builder deliveryInf(DeliveryInf val) {
            deliveryInf = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder products(Map<Product,Integer> val) {
            products = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
