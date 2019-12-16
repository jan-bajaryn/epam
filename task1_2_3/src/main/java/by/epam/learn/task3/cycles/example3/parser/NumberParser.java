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

        if (!isValid(arr)) {
            throw new IllegalArgumentException();
        }

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

    private boolean isValid(char[] arr) {

        if (arr == null || arr.length==0)
            return false;

        int prev = arr[0];
        int repeatNum = 1;


        for (int i = 0; i < arr.length; i++) {
            if (!voc.containsKey(arr[i])) {
                return false;
            }
            int f = voc.get(arr[i]);

            if (prev == f) {
                repeatNum++;
            } else {
                repeatNum = 1;
                prev = f;
            }

            if (i < arr.length - 1) {
                if (!voc.containsKey(arr[i + 1])) {
                    return false;
                }

                int s = voc.get(arr[i + 1]);

                if (f < s) {
                    //добавление
                    if (!((f * 5 == s) || (f * 10 == s))) {
                        return false;
                    }
                    prev = s;
                    i++;
                }
            }

            if (repeatNum >= 4)
                return false;

        }
        return true;
    }


}
