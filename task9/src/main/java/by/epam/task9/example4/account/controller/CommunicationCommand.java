package by.epam.task9.example4.account.controller;


import by.epam.task9.example4.account.dao.AccountRepo;
import by.epam.task9.example4.account.dao.UserRepo;
import by.epam.task9.example4.account.dao.reader.TextFileReader;
import by.epam.task9.example4.account.entity.Pair;
import by.epam.task9.example4.account.parser.AccountParser;
import by.epam.task9.example4.account.parser.WrongDataException;
import by.epam.task9.example4.account.view.ConsoleCommander;
import by.epam.task9.example4.account.view.ConsoleReader;

import java.io.IOException;
import java.util.List;

public class CommunicationCommand {
    private ReadAccountsFromConsole readAccountsFromConsole = new ReadAccountsFromConsole();
    private ConsoleCommander consoleCommander = new ConsoleCommander();
    private TextFileReader textFileReader = new TextFileReader();
    private AccountParser accountParser = new AccountParser();
    private UserRepo userRepo = UserRepo.getInstance();
    private AccountRepo accountRepo = AccountRepo.getInstance();
    private GeneralCommand generalCommand = new GeneralCommand();

    public void execute() {
        boolean isDone = false;
        while (!isDone) {
            switch (consoleCommander.readFirstCommand()) {
                case ConsoleCommander.READING_FILE:
                    try {
                        String[] s = textFileReader.readLinesFromFile(consoleCommander.readFileName());
                        List<Pair> pairs = accountParser.stringToAccountList(s);
                        for (Pair pair : pairs) {
                            userRepo.save(pair.getUser());
                            accountRepo.save(pair.getAccount());
                        }
                        generalCommand.execute();
                    } catch (IOException e) {
                        System.out.println("No such file found.");
                    } catch (WrongDataException e) {
                        System.out.println("Wrong format in text file.");
                    }
                    break;
                case ConsoleCommander.SELF_INPUT:
                    readAccountsFromConsole.execute();
                    generalCommand.execute();
                    break;
                case ConsoleCommander.EXIT:
                    isDone = true;
                    break;
                default:
                    System.out.println("Your input is incorrect. Please try again.");
            }
        }

    }
}
