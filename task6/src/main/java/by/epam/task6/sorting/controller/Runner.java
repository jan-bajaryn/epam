package by.epam.task6.sorting.controller;

import by.epam.task6.sorting.service.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        Sort[] sorts = {new BubleSort(),
                new InsertBinarySort(),
                new InsertSort(),
                new SelectSort(),
                new SelectTwoSideSort(),
                new ShakerSort(),
                new MergeSort(),
                new QuickSort()
        };
        DisplayTestSortsCommand displayTestSortsCommand = new DisplayTestSortsCommand();
        displayTestSortsCommand.execute(sorts);
    }


}
