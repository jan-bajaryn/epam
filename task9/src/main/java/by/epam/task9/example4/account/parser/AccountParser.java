package by.epam.task9.example4.account.parser;

import by.epam.task9.example4.account.dao.UserRepo;
import by.epam.task9.example4.account.entity.Account;
import by.epam.task9.example4.account.entity.Pair;
import by.epam.task9.example4.account.entity.User;

import java.util.ArrayList;
import java.util.List;

public class AccountParser {
    public static final String SPLITER = "---";
    private UserRepo userRepo = UserRepo.getInstance();


    public String accountToString(Account account) {
        StringBuilder sb = new StringBuilder();
        sb.append("Account: \n");
        User user = userRepo.getUserById(account.getUserId());
        sb.append("User:")
                .append(account.getUserId())
                .append(SPLITER).append(user.getName())
                .append(SPLITER).append(user.getSurname())
                .append("\n");
        sb.append("Number:").append(account.getNumber()).append("\n");
        sb.append("Amount:").append(account.getAmount()).append("\n");
        sb.append("IsActive:").append(account.isActive());
        return sb.toString();
    }

    public String accountListToString(List<Account> accounts) {
        return accounts.stream()
                .map(this::accountToString)
                .reduce((s1, s2) -> s1.concat("\n").concat(s2)).orElse("");
    }

    public Pair stringToAccount(String data) throws WrongDataException {
        String[] split = data.split("\n");
        if (split.length != 5) {
            throw new WrongDataException();
        }
        String[] splitUser = split[1].split(SPLITER);
        int userId = Integer.parseInt(splitUser[0].substring(5, splitUser[0].length()));
        User user = new User(userId, splitUser[1], splitUser[2]);
        int number = Integer.parseInt(split[2].substring("Number:".length()));
        int amount = Integer.parseInt(split[3].substring("Amount:".length()));
        boolean isActive = Boolean.parseBoolean(split[4].substring("IsActive:".length()));
        Account account = new Account(number, user.getId(), amount, isActive);
        return new Pair(user, account);
    }

    public List<Pair> stringToAccountList(String[] data) throws WrongDataException {
        if (data == null || data.length % 5 != 0) {
            throw new WrongDataException("Wrong count of rows.");
        }

        List<Pair> pairs = new ArrayList<>();
        String string = "";
        for (int i = 0; i < data.length; i += 5) {
            for (int j = 0; j < 5; j++) {
                string += data[i + j] + "\n";
            }
            pairs.add(stringToAccount(string));
            string = "";
        }
        return pairs;
    }

    public Pair paramsToAccountPair(String[] params) throws WrongDataException {
        try {
            int userId = Integer.parseInt(params[0]);
            User user = new User(userId, params[1], params[2]);
            int number = Integer.parseInt(params[3]);
            int amount = Integer.parseInt(params[4]);
            boolean isActive = Boolean.parseBoolean(params[5]);
            Account account = new Account(number, user.getId(), amount, isActive);
            return new Pair(user, account);
        } catch (NumberFormatException e) {
            throw new WrongDataException();
        }
    }
}
