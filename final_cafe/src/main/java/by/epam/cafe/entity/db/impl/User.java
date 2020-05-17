package by.epam.cafe.entity.db.impl;


import by.epam.cafe.entity.db.Entity;
import by.epam.cafe.entity.enums.Role;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User extends Entity<Integer> implements Serializable {

    private String username;

    private String password;

    private Role role;

    private String name;
    private String surname;

    private LocalDateTime creation;

    private String street;
    private String house;
    private String room;
    private Integer porch;
    private Integer floor;

    private String phone;

    private String email;

    private boolean blocked;


    public User() {
    }

    public User(Integer integer, String username, String password, Role role, String name, String surname, LocalDateTime creation, String street, String house, String room, Integer porch, Integer floor, String phone, String email, boolean blocked) {
        super(integer);
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.creation = creation;
        this.street = street;
        this.house = house;
        this.room = room;
        this.porch = porch;
        this.floor = floor;
        this.phone = phone;
        this.email = email;
        this.blocked = blocked;
    }

    private User(Builder builder) {
        setId(builder.id);
        setUsername(builder.username);
        setPassword(builder.password);
        setRole(builder.role);
        setName(builder.name);
        setSurname(builder.surname);
        setCreation(builder.creation);
        setStreet(builder.street);
        setHouse(builder.house);
        setRoom(builder.room);
        setPorch(builder.porch);
        setFloor(builder.floor);
        setPhone(builder.phone);
        setEmail(builder.email);
        blocked = builder.isBlocked;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (blocked != user.blocked) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != user.role) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (creation != null ? !creation.equals(user.creation) : user.creation != null) return false;
        if (street != null ? !street.equals(user.street) : user.street != null) return false;
        if (house != null ? !house.equals(user.house) : user.house != null) return false;
        if (room != null ? !room.equals(user.room) : user.room != null) return false;
        if (porch != null ? !porch.equals(user.porch) : user.porch != null) return false;
        if (floor != null ? !floor.equals(user.floor) : user.floor != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (creation != null ? creation.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (porch != null ? porch.hashCode() : 0);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (blocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                " id=" + getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", creation=" + creation +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", room='" + room + '\'' +
                ", porch=" + porch +
                ", floor=" + floor +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", isBlocked=" + blocked +
                '}';
    }

    public static final class Builder {
        private Integer id;
        private String username;
        private String password;
        private Role role;
        private String name;
        private String surname;
        private LocalDateTime creation;
        private String street;
        private String house;
        private String room;
        private Integer porch;
        private Integer floor;
        private String phone;
        private String email;
        private boolean isBlocked;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder role(Role val) {
            role = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder surname(String val) {
            surname = val;
            return this;
        }

        public Builder creation(LocalDateTime val) {
            creation = val;
            return this;
        }

        public Builder street(String val) {
            street = val;
            return this;
        }

        public Builder house(String val) {
            house = val;
            return this;
        }

        public Builder room(String val) {
            room = val;
            return this;
        }

        public Builder porch(Integer val) {
            porch = val;
            return this;
        }

        public Builder floor(Integer val) {
            floor = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder isBlocked(boolean val) {
            isBlocked = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

