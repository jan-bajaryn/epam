package by.epam.task9.example4.account.controller;

import by.epam.task9.example4.account.dao.AccountRepo;
import by.epam.task9.example4.account.dao.UserRepo;
import by.epam.task9.example4.account.entity.Account;
import by.epam.task9.example4.account.entity.Pair;
import by.epam.task9.example4.account.parser.AccountParser;
import by.epam.task9.example4.account.parser.WrongDataException;
import by.epam.task9.example4.account.view.ConsoleCommander;
import by.epam.task9.example4.account.view.ConsoleReader;

import java.util.ArrayList;
import java.util.List;

public class ReadAccountsFromConsole {
    private AccountParser accountParser = new AccountParser();
    private ConsoleReader consoleReader = new ConsoleReader();
    private ConsoleCommander consoleCommander = new ConsoleCommander();
    private AccountRepo accountRepo = AccountRepo.getInstance();
    private UserRepo userRepo = UserRepo.getInstance();

    public void execute() {
        boolean isDone = false;
        List<Account> list = new ArrayList<>();
        while (!isDone) {
            switch (consoleCommander.isContinue()) {
                case ConsoleCommander.CONTINUE:
                    try {
                        String[] strings = consoleReader.readParamsAccount();
                        Pair pair = accountParser.paramsToAccountPair(strings);
                        userRepo.save(pair.getUser());
                        accountRepo.save(pair.getAccount());
                    } catch (WrongDataException e) {
                        System.out.println("Wrong parameters input.");
                    }
                    break;
                default:
                    isDone = true;
            }
        }
    }
}
