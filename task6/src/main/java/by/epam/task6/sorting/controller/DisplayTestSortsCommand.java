package by.epam.task6.sorting.controller;

import by.epam.task6.sorting.service.Sort;

public class DisplayTestSortsCommand {
    private SortThroughAllCommand throughAllCommand;

    public DisplayTestSortsCommand() {
        throughAllCommand = new SortThroughAllCommand();
    }

    public void execute(Sort[] sorts) {
        for (Sort sort : sorts) {
            throughAllCommand.printSort(sort);
        }
    }
}
