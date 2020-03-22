package by.epam.cafe.entity.impl;


import by.epam.cafe.entity.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DeliveryInf extends Entity<Integer> implements Serializable {

    private LocalDateTime deliveryTime;

    private String street;
    private String house;
    private String room;
    private Integer porch;
    private Integer floor;


    private String phone;
    private String email;
    private String comments;

    private Order order;

    public DeliveryInf() {
    }

    public DeliveryInf(Integer integer,
                       LocalDateTime deliveryTime,
                       String street,
                       String house,
                       String room,
                       Integer porch,
                       Integer floor,
                       String phone,
                       String email,
                       String comments,
                       Order order) {
        super(integer);
        this.deliveryTime = deliveryTime;
        this.street = street;
        this.house = house;
        this.room = room;
        this.porch = porch;
        this.floor = floor;
        this.phone = phone;
        this.email = email;
        this.comments = comments;
        this.order = order;
    }

    private DeliveryInf(Builder builder) {
        setId(builder.id);
        setDeliveryTime(builder.deliveryTime);
        setStreet(builder.street);
        setHouse(builder.house);
        setRoom(builder.room);
        setPorch(builder.porch);
        setFloor(builder.floor);
        setPhone(builder.phone);
        setEmail(builder.email);
        setComments(builder.comments);
        setOrder(builder.order);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getPorch() {
        return porch;
    }

    public void setPorch(Integer porch) {
        this.porch = porch;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryInf that = (DeliveryInf) o;

        if (deliveryTime != null ? !deliveryTime.equals(that.deliveryTime) : that.deliveryTime != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (house != null ? !house.equals(that.house) : that.house != null) return false;
        if (room != null ? !room.equals(that.room) : that.room != null) return false;
        if (porch != null ? !porch.equals(that.porch) : that.porch != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        return order != null ? order.equals(that.order) : that.order == null;
    }

    @Override
    public int hashCode() {
        int result = deliveryTime != null ? deliveryTime.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (porch != null ? porch.hashCode() : 0);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeliveryInf{" +
                "deliveryTime=" + deliveryTime +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", room='" + room + '\'' +
                ", porch=" + porch +
                ", floor=" + floor +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", comments='" + comments + '\'' +
                ", order=" + order +
                '}';
    }

    public static final class Builder {
        private Integer id;
        private LocalDateTime deliveryTime;
        private String street;
        private String house;
        private String room;
        private Integer porch;
        private Integer floor;
        private String phone;
        private String email;
        private String comments;
        private Order order;

        private Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder deliveryTime(LocalDateTime deliveryTime) {
            this.deliveryTime = deliveryTime;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder house(String house) {
            this.house = house;
            return this;
        }

        public Builder room(String room) {
            this.room = room;
            return this;
        }

        public Builder porch(Integer porch) {
            this.porch = porch;
            return this;
        }

        public Builder floor(Integer floor) {
            this.floor = floor;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public DeliveryInf build() {
            return new DeliveryInf(this);
        }
    }
}
