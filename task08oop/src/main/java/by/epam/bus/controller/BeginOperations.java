package by.epam.bus.controller;

import by.epam.bus.dao.reader.CommandsConsoleReader;
import by.epam.bus.view.ConsolePrinter;


public class BeginOperations {
    private CommandsConsoleReader commandsConsoleReader = new CommandsConsoleReader();
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private FileReadCommand fileReadCommand = new FileReadCommand();


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
