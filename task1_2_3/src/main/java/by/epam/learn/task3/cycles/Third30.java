package by.epam.learn.task3.cycles;

import java.util.Map;
import java.util.TreeMap;

public class Third30 {
    //    function convert(roman) {
//        var values = {I: 1, V: 5, X: 10, L: 50, C: 100, D: 500, M: 1000};
//        var digits = Object.keys(values);
//
//        roman = roman.toUpperCase();
//        var res = 0;
//
//        for (var q=0; q<roman.length; ++q) {
//            if (digits.indexOf(roman[q]) < digits.indexOf(roman[q+1])) {
//                res -= values[roman[q]];
//            } else {
//                res += values[roman[q]];
//            }
//        }
//
//        return res;
//    }

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

    private int toArabian(String roman) {
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

    public static void main(String[] args) {
        Third30 third30 = new Third30();
        System.out.println(third30.toArabian("XVI"));
    }
}
