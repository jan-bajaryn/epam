package by.epam.task9.example4.account.controller;

import by.epam.task9.example4.account.entity.Account;
import by.epam.task9.example4.account.view.AccountListPrinter;
import by.epam.task9.example4.account.view.ConsoleCommander;
import by.epam.task9.example4.account.view.WriteAccountListToFile;

import java.io.IOException;
import java.util.List;

public class WriteOrPrint {
    private ConsoleCommander consoleCommander = new ConsoleCommander();
    private WriteAccountListToFile writeAccountListToFile = new WriteAccountListToFile();
    private AccountListPrinter accountListPrinter = new AccountListPrinter();

    public void execute(List<Account> list) {
        boolean isDone = false;
        while (!isDone) {
            switch (consoleCommander.readDesisionWrite()) {
                case ConsoleCommander.WRITE:
                    try {
                        writeAccountListToFile.write(consoleCommander.readFileName(), list);
                        System.out.println("\n");
                        isDone = true;
                    } catch (IOException e) {
                        System.out.println("Problems with writing in file.");
                    }
                    break;
                case ConsoleCommander.PRINT:
                    accountListPrinter.print(list);
                    isDone = true;
                    break;
                default:
                    System.out.println("Your input is incorrect.");
            }
        }
    }
}
