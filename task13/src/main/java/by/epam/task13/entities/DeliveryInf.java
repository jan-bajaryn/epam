package by.epam.task13.entities;

import java.time.LocalDateTime;

public class DeliveryInf {
    private LocalDateTime deliveryTime;
    private String clientName;
    private String address;
    private String phone;
    private String email;

    public DeliveryInf(LocalDateTime deliveryTime, String clientName, String address, String phone, String email) {
        this.deliveryTime = deliveryTime;
        this.clientName = clientName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public static DeliveryInf.Builder builder() {
        return new DeliveryInf.Builder();
    }

    public DeliveryInf() {
    }

    private DeliveryInf(Builder builder) {
        setDeliveryTime(builder.deliveryTime);
        setClientName(builder.clientName);
        setAddress(builder.address);
        setPhone(builder.phone);
        setEmail(builder.email);
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryInf that = (DeliveryInf) o;

        if (deliveryTime != null ? !deliveryTime.equals(that.deliveryTime) : that.deliveryTime != null) return false;
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = deliveryTime != null ? deliveryTime.hashCode() : 0;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeliveryInf{" + "\n" +
                "deliveryTime=" + deliveryTime + "\n" +
                ", clientName='" + clientName + '\'' + "\n" +
                ", address='" + address + '\'' + "\n" +
                ", phone='" + phone + '\'' + "\n" +
                ", email='" + email + '\'' + "\n" +
                '}' + "\n";
    }

    public static final class Builder {
        private LocalDateTime deliveryTime;
        private String clientName;
        private String address;
        private String phone;
        private String email;

        public Builder() {
        }

        public Builder deliveryTime(LocalDateTime val) {
            deliveryTime = val;
            return this;
        }

        public Builder clientName(String val) {
            clientName = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
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

        public DeliveryInf build() {
            return new DeliveryInf(this);
        }
    }
}
