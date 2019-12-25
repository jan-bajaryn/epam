package by.epam.bus.controller;

import by.epam.bus.reader.CommandsConsoleReader;
import by.epam.bus.repos.BusStation;
import by.epam.bus.view.ConsolePrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class ChooseOperations {
    private CommandsConsoleReader commandsConsoleReader;
    private ConsolePrinter consolePrinter;

    public ChooseOperations() {
        commandsConsoleReader = new CommandsConsoleReader();
        consolePrinter = new ConsolePrinter();
    }

    public void execute(BusStation busStation) {
        boolean isDone = false;

        while (!isDone) {

            String choose = commandsConsoleReader.chooseOperations();
            switch (choose) {
                case CommandsConsoleReader.BUSES_BY_TRACK:

                    break;
                case CommandsConsoleReader.MORE_EXPLOITATION:
                    break;
                case CommandsConsoleReader.MORE_MILLAGE:
                    break;
                case CommandsConsoleReader.DRIVER:
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
