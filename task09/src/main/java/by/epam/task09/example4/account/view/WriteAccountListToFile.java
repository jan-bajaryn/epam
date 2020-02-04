package by.epam.task09.example4.account.view;

import by.epam.task09.example4.account.entity.Account;
import by.epam.task09.example4.account.parser.AccountParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WriteAccountListToFile {
    private AccountParser accountParser = new AccountParser();

    public void write(String fileName, List<Account> accounts) throws IOException {
        Files.write(Paths.get(fileName), accountParser.accountListToString(accounts).getBytes());

    }
}
