package by.epam.task9.example4.account.view;

import by.epam.task9.example4.account.entity.Account;
import by.epam.task9.example4.account.parser.AccountParser;

import java.util.List;

public class AccountListPrinter {
    private AccountParser accountParser = new AccountParser();

    public void print(List<Account> list) {
        String s = accountParser.accountListToString(list);
        System.out.println(s);
    }

}
