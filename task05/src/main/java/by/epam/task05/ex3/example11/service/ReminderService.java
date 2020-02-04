package by.epam.task05.ex3.example11.service;

import by.epam.task05.ex3.example11.service.exception.IllegalInputArgumentException;
import by.epam.task05.ex3.example11.service.exception.NullArrayException;

import java.util.ArrayList;
import java.util.List;

public class ReminderService {
    public Integer[] findNumNoReminder(int[] arr, int m, int l) throws NullArrayException, IllegalInputArgumentException {
        if (l < 0 || m - 1 < l) {
            throw new IllegalInputArgumentException();
        }
        if (arr == null) {
            throw new NullArrayException();
        }
        List<Integer> numbers = new ArrayList<>();
        for (int value : arr) {
            if (value % m == l) {
                numbers.add(value);
            }
        }
        return numbers.toArray(new Integer[numbers.size()]);
    }
}
