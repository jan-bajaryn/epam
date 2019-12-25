package by.epam.bus.controller;

import by.epam.bus.dao.reader.CommandsConsoleReader;
import by.epam.bus.view.ConsolePrinter;

import java.util.Scanner;

public class BeginOperations {
    private CommandsConsoleReader commandsConsoleReader;
    private Scanner sc;
    private ConsolePrinter consolePrinter;
    private FileReadCommand fileReadCommand;

    public BeginOperations() {
        commandsConsoleReader = new CommandsConsoleReader();
        sc = new Scanner(System.in);
        consolePrinter = new ConsolePrinter();
        fileReadCommand = new FileReadCommand();
    }

    public void execute() {
        boolean isDone = false;

        while (!isDone) {

            String choose = commandsConsoleReader.chooseCommandBegin();
            switch (choose) {
                case CommandsConsoleReader.READING_FILE:
                    fileReadCommand.execute();
                    break;
                case CommandsConsoleReader.EXIT:
                    isDone = true;
                    break;
                default:
                    consolePrinter.errorMessage();
            }
        }
    }

}
