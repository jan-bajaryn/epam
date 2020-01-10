package by.epam.task9.example4.account.controller;

import by.epam.task9.example4.account.dao.AccountRepo;
import by.epam.task9.example4.account.dao.UserRepo;
import by.epam.task9.example4.account.entity.Account;
import by.epam.task9.example4.account.view.AmountPrinter;
import by.epam.task9.example4.account.view.ConsoleCommander;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralCommand {
    private WriteOrPrint writeOrPrint = new WriteOrPrint();
    private ConsoleCommander consoleCommander = new ConsoleCommander();
    private AccountRepo accountRepo = AccountRepo.getInstance();
    private UserRepo userRepo = UserRepo.getInstance();
    private AmountPrinter amountPrinter = new AmountPrinter();

    public void execute() {
        boolean isDone = false;
        while (!isDone) {
            switch (consoleCommander.readSecondCommand()) {
                case ConsoleCommander.FIND_BY_USER_ID:
                    Account byUserId = accountRepo.findByUserId(consoleCommander.readUserId());
                    if (byUserId != null) {
                        writeOrPrint.execute(new ArrayList<Account>(Arrays.asList(byUserId)));
                    } else {
                        System.out.println("There no such user found.");
                    }
                    break;
                case ConsoleCommander.FIND_MORE_AMOUNT:
                    List<Account> moreAmount = accountRepo.findMoreAmount(consoleCommander.readAmount());
                    writeOrPrint.execute(moreAmount);
                    break;
                case ConsoleCommander.FIND_LESS_EQ_AMOUNT:
                    List<Account> lessOrEqualAmount = accountRepo.findLessOrEqualAmount(consoleCommander.readAmount());
                    writeOrPrint.execute(lessOrEqualAmount);
                    break;
                case ConsoleCommander.FIND_ACTIVE:
                    writeOrPrint.execute(accountRepo.findActive());
                    break;
                case ConsoleCommander.FIND_PASSIVE:
                    writeOrPrint.execute(accountRepo.findNotActive());
                    break;
                case ConsoleCommander.SORT_BY_USERNAME:
                    writeOrPrint.execute(accountRepo.getAllSortedByUserName());
                    break;
                case ConsoleCommander.SORT_BY_SURNAME:
                    writeOrPrint.execute(accountRepo.getAllSortedByUserSurname());
                    break;
                case ConsoleCommander.AMOUNT_BY_USER_ID:
                    amountPrinter.print(accountRepo.allAmountByUserId(consoleCommander.readUserId()));
                    break;
                case ConsoleCommander.SUM_AMOUNT_MORE_ZERO:
                    amountPrinter.print(accountRepo.sumAmountMoreThanZero());
                    break;
                case ConsoleCommander.SUM_AMOUNT_LESS_ZERO:
                    amountPrinter.print(accountRepo.sumAmountLessThanZero());
                    break;
                case ConsoleCommander.EXIT:
                    accountRepo.clear();
                    userRepo.clear();
                    isDone = true;
                    break;
                default:
                    System.out.println("Your input is incorrect. Please try again.");
            }
        }

    }
}
