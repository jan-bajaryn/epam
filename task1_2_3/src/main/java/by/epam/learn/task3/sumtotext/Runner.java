package by.epam.learn.task3.sumtotext;

import by.epam.learn.task3.sumtotext.parser.SumToTextParser;

public class Runner {
    public static void main(String[] args) {
        SumToTextParser sumToTextParser = new SumToTextParser();
        System.out.println(sumToTextParser.parseText(0));
//        System.out.println(Character.getNumericValue('0'));
    }
}
