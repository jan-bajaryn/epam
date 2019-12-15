package by.epam.learn.task3.cycles.example3;

import by.epam.learn.task3.cycles.example3.parser.NumberParser;

import java.util.Map;
import java.util.TreeMap;

public class Runner {
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

    public static void main(String[] args) {
        NumberParser numberParser = new NumberParser();
        System.out.println(numberParser.toArabian("XVI"));
    }
}
