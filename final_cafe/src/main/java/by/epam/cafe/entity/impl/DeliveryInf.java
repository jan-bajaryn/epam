package by.epam.cafe.entity.impl;


import by.epam.cafe.entity.Entity;

import java.time.LocalDateTime;

public class DeliveryInf extends Entity<Integer> {

    private LocalDateTime deliveryTime;

    private String street;
    private String house;
    private String room;
    private String porch;
    private String floor;


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
                       String porch,
                       String floor,
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

    public String getPorch() {
        return porch;
    }

    public void setPorch(String porch) {
        this.porch = porch;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
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

}
