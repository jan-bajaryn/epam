package by.epam.cafe.entity.impl;

import by.epam.cafe.entity.Entity;

import java.time.LocalDateTime;

public class Order extends Entity<Long> {
    //    private Long id;
    private LocalDateTime creation;
    private Integer price;
    private Integer status;
    private Integer paymentType;

    public Order() {
    }

    public Order(Long id, LocalDateTime creation, Integer price, Integer status, Integer paymentType) {
        super(id);
//        this.id = id;
        this.creation = creation;
        this.price = price;
        this.status = status;
        this.paymentType = paymentType;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getId() != null ? !getId().equals(order.getId()) : order.getId() != null) return false;
        if (creation != null ? !creation.equals(order.creation) : order.creation != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        return paymentType != null ? paymentType.equals(order.paymentType) : order.paymentType == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (creation != null ? creation.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + getId() +
                ", creation=" + creation +
                ", price=" + price +
                ", status=" + status +
                ", paymentType=" + paymentType +
                '}';
    }
}


