package by.epam.task13.entities;

import by.epam.task13.entities.enums.OrderStatus;
import by.epam.task13.entities.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Integer id;

    private LocalDateTime creation;
    private Integer price;
    private OrderStatus status;
    private PaymentType paymentType;
    private DeliveryInf deliveryInf;
    private List<Product> products;

    public static Builder builder() {
        return new Builder();
    }

    public Order() {
    }

    public Order(Integer id,
                 LocalDateTime creation,
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

    private Order(Builder builder) {
        setId(builder.id);
        setCreation(builder.creation);
        setPrice(builder.price);
        setStatus(builder.status);
        setPaymentType(builder.paymentType);
        setDeliveryInf(builder.deliveryInf);
        setProducts(builder.products);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
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
                "id='" + id + '\'' +"\n"+
                ", creation=" + creation +"\n"+
                ", price=" + price +"\n"+
                ", status=" + status +"\n"+
                ", paymentType=" + paymentType +"\n"+
                ", deliveryInf=" + deliveryInf +"\n"+
                ", products=" + products +"\n"+
                '}'+"\n";
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

    public static final class Builder {
        private Integer id;
        private LocalDateTime creation;
        private Integer price;
        private OrderStatus status;
        private PaymentType paymentType;
        private DeliveryInf deliveryInf;
        private List<Product> products;

        public Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder creation(LocalDateTime val) {
            creation = val;
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

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Order build() {
            if (products == null) {
                products = new ArrayList<>();
            }
            return new Order(this);
        }
    }
}
