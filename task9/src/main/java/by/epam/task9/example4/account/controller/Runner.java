package by.epam.task9.example4.account.controller;

import by.epam.task9.example4.account.dao.AccountRepo;
import by.epam.task9.example4.account.dao.UserRepo;
import by.epam.task9.example4.account.dao.reader.TextFileReader;
import by.epam.task9.example4.account.entity.Account;
import by.epam.task9.example4.account.entity.User;
import by.epam.task9.example4.account.parser.AccountParser;
import by.epam.task9.example4.account.parser.WrongDataException;
import by.epam.task9.example4.account.view.WriteAccountListToFile;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) {
        AccountParser accountParser = new AccountParser();
        TextFileReader textFileReader = new TextFileReader();
        WriteAccountListToFile writeAccountListToFile = new WriteAccountListToFile();
        UserRepo userRepo = UserRepo.getInstance();
        AccountRepo accountRepo = AccountRepo.getInstance();
        User user = new User("Jan", "Kozlov");
        Account account = new Account(user.getId(), 24, true);
        userRepo.save(user);
        accountRepo.save(account);

        user = new User("Pawel", "Kuznecov");
        account = new Account(user.getId(), 2, true);
        userRepo.save(user);
        accountRepo.save(account);

        user = new User("III", "CCC");
        account = new Account(user.getId(), 345, true);
        userRepo.save(user);
        accountRepo.save(account);


//        try {
//            writeAccountListToFile.write("iha.txt", accountRepo.findActive());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            String[] s = textFileReader.readLinesFromFile("my.txt");
            System.out.println(accountParser.stringToAccountList(s));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongDataException e) {
            e.printStackTrace();
        }
    }
}
