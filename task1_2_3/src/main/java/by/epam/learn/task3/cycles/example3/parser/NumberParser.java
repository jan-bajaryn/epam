package by.epam.learn.task3.cycles.example3.parser;

import java.util.*;

public class NumberParser {

    private static Map<Character, Integer> voc = new TreeMap<>();

    static {
        voc.put('I', 1);
        voc.put('V', 5);
        voc.put('X', 10);
        voc.put('L', 50);
        voc.put('C', 100);
        voc.put('D', 500);
        voc.put('M', 1000);
    }

    public int toArabian(String roman) {
        int result = 0;
        char[] arr = roman.toCharArray();

        for (int i = 0; i < arr.length; i++) {

            int f = voc.get(arr[i]);

            if (i < arr.length - 1) {
                int s = voc.get(arr[i + 1]);

                if (f >= s) {
                    result += f;
                } else {
                    result += s;
                    result -= f;
                    i++;
                }
            } else {
                result += f;
                i++;
            }

        }

        return result;
    }


}
