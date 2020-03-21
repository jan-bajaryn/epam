package by.epam.cafe.entity.impl;


import by.epam.cafe.entity.Entity;
import by.epam.cafe.entity.enums.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User extends Entity<Integer> {

    private String username;

    private String password;

    private Role role;

    private String name;
    private String surname;

    private LocalDateTime creation;

    private String street;
    private String house;
    private String room;
    private String porch;
    private String floor;

    private String phone;

    private String email;

    private Boolean isBlocked;

    private List<Order> orders = new ArrayList<>();


}

