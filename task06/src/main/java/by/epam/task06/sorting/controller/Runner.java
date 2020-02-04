package by.epam.task06.sorting.controller;

import by.epam.task06.sorting.service.*;

public class Runner {
    public static void main(String[] args) {
        Sort[] sorts = {new BubleSort(),
                new InsertBinarySort(),
                new InsertSort(),
                new SelectSort(),
                new SelectTwoSideSort(),
                new ShakerSort(),
                new MergeSort()
        };
        DisplayTestSortsCommand displayTestSortsCommand = new DisplayTestSortsCommand();
        displayTestSortsCommand.execute(sorts);
    }


}
