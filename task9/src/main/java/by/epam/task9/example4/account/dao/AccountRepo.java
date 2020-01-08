package by.epam.task9.example4.account.dao;

import by.epam.task9.example4.account.entity.Account;
import by.epam.task9.example4.account.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountRepo {
    private static AccountRepo singleton = new AccountRepo();

    public static AccountRepo getInstance() {
        return singleton;
    }

    private AccountRepo() {
    }

    private List<Account> accounts = new ArrayList<>();
    private UserRepo userRepo = UserRepo.getInstance();

    public Account findByUserId(int id) {
        return accounts.stream()
                .filter(account -> account.getUserId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Account> findMoreAmount(int amount) {
        return accounts.stream()
                .filter(account -> account.getAmount() > amount)
                .collect(Collectors.toList());
    }

    public List<Account> findLessOrEqualAmount(int amount) {
        return accounts.stream()
                .filter(account -> account.getAmount() <= amount)
                .collect(Collectors.toList());
    }

    public List<Account> findActive() {
        return accounts.stream()
                .filter(Account::isActive)
                .collect(Collectors.toList());
    }

    public List<Account> findNotActive() {
        return accounts.stream()
                .filter(account -> !account.isActive())
                .collect(Collectors.toList());
    }

    public void save(Account account) {
        Account temp = accounts.stream()
                .filter(a -> a.getNumber() == account.getNumber())
                .findAny()
                .orElse(null);
        if (temp == null) {
            if (userRepo.getUserById(account.getUserId()) != null) {
                accounts.add(account);
            }
        } else {
            if (userRepo.getUserById(account.getUserId()) != null) {
                temp.setAmount(account.getAmount());
                temp.setActive(account.isActive());
                temp.setUserId(account.getUserId());
            }

        }
    }

    public List<Account> getAllSortedByUserName() {
        return accounts.stream()
                .sorted((o1, o2) -> {
                    User first = userRepo.getUserById(o1.getUserId());
                    User sec = userRepo.getUserById(o2.getUserId());

                    return first.getName().compareTo(sec.getName());
                })
                .collect(Collectors.toList());
    }

    public List<Account> getAllSortedByUserSurname() {
        return accounts.stream()
                .sorted((o1, o2) -> {
                    User first = userRepo.getUserById(o1.getUserId());
                    User sec = userRepo.getUserById(o2.getUserId());

                    return first.getSurname().compareTo(sec.getSurname());
                })
                .collect(Collectors.toList());
    }

    public int allAmountByUserId(int id) {
        return accounts.stream()
                .filter(a -> a.getUserId() == id)
                .map(Account::getAmount)
                .reduce(Integer::sum).orElse(0);
    }

    public int sumAmountMoreThanZero() {
        return accounts.stream()
                .filter(a -> a.getAmount() > 0)
                .map(Account::getAmount)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public int sumAmountLessThanZero() {
        return accounts.stream()
                .filter(a -> a.getAmount() < 0)
                .map(Account::getAmount)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public int lastId() {
        return accounts.stream()
                .map(account -> account.getNumber())
                .max(Integer::compareTo)
                .orElse(null);
    }
}
