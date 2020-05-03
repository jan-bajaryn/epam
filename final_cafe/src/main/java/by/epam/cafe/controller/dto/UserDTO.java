package by.epam.cafe.controller.dto;

import by.epam.cafe.entity.enums.Role;
import by.epam.cafe.entity.impl.User;

import java.time.LocalDateTime;

public class UserDTO {
    private Integer id;
    private String username;

    private Role role;

    private String name;
    private String surname;

    private String street;
    private String house;
    private String room;
    private Integer porch;
    private Integer floor;

    private String phone;

    private String email;



    public UserDTO() {
    }


    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.street = user.getStreet();
        this.house = user.getHouse();
        this.room = user.getRoom();
        this.porch = user.getPorch();
        this.floor = user.getFloor();
        this.phone = user.getPhone();
        this.email = user.getEmail();
    }


    public UserDTO(Integer id,String username, Role role, String name, String surname, String street, String house, String room, Integer porch, Integer floor, String phone, String email) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.house = house;
        this.room = room;
        this.porch = porch;
        this.floor = floor;
        this.phone = phone;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
