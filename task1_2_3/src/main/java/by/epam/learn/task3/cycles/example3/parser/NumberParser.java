package by.epam.learn.task3.cycles.example3.parser;

import java.util.Map;
import java.util.TreeMap;

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
        for (int i = 0; i < roman.length(); i++) {
//            int current = voc.get(roman.charAt(i));
            int temp = voc.get(roman.charAt(i));
            char current = (char) temp;
            int next = voc.get(roman.charAt(i + 1));
            if (i < roman.length() - 1 && current < next) {
                result -= voc.get(current);
            } else {
                result += voc.get(current);
            }
        }
        return result;
    }

}
