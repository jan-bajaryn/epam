package by.epam.task09.example4.account.entity;

public class Pair {
    private User user;
    private Account account;

    public Pair(User user, Account account) {
        this.user = user;
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "user=" + user +
                ", account=" + account +
                '}';
    }
}
