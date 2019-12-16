package by.epam.learn.task3.cycles.example3;

import by.epam.learn.task3.cycles.example3.parser.NumberParser;

import java.util.Map;
import java.util.TreeMap;

public class Runner {

    public static void main(String[] args) {
        NumberParser numberParser = new NumberParser();
        System.out.println(numberParser.toArabian("CCXLIX"));
        System.out.println(numberParser.toArabian("CCCXXXVII"));


    }
}
