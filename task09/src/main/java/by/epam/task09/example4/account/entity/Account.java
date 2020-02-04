package by.epam.task09.example4.account.entity;

public class Account {
    private static int numberCounter = 1;

    private int number;
    private int userId;
    private int amount;
    private boolean isActive;


    public Account(int userId, int amount, boolean isActive) {
        number = numberCounter++;
        this.userId = userId;
        this.amount = amount;
        this.isActive = isActive;
    }

    public Account(int number, int userId, int amount, boolean isActive) {
        this.number = number;
        this.userId = userId;
        this.amount = amount;
        this.isActive = isActive;
        if (numberCounter <= number) {
            numberCounter = number + 1;
        }
    }

    public int getUserId() {
        return userId;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getNumber() {
        return number;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (userId != account.userId) return false;
        if (amount != account.amount) return false;
        return isActive == account.isActive;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + amount;
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", userId=" + userId +
                ", amount=" + amount +
                ", isActive=" + isActive +
                '}';
    }
}
